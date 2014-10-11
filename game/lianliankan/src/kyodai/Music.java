package kyodai;

import javax.sound.midi.*;
import java.io.*;
import java.net.*;

/**
 * <p>Title: LianLianKan</p>
 * <p>Description: 连连看</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: www.wuhantech.com</p>
 * @author ZhangJian
 * @version 1.0
 */

public class Music
    implements MetaEventListener, Runnable {
  private String midiFile = "sound/bg.mid";
  private Sequence sequence = null;
  private Sequencer sequencer;
  private boolean isPlaying = false;
  private volatile Thread thread;

  public Music() {
    try {
      loadMidi(midiFile);
    }
    catch (InvalidMidiDataException ex) {
    }
    catch (IOException ex) {
    }
  }

  /**
   * 读取midi文件
   * @param filename
   */
  public void loadMidi(String filename) throws IOException,
      InvalidMidiDataException {
    URLClassLoader urlLoader = (URLClassLoader)this.getClass().getClassLoader();
    URL url = urlLoader.findResource(filename);
    sequence = MidiSystem.getSequence(url);
  }

  public void play() {
    if (isPlaying) { //如果已经在播放，返回
      return;
    }

    try {
      // Create a sequencer for the sequence
      sequencer = MidiSystem.getSequencer();
      sequencer.open();
      sequencer.setSequence(sequence);

      sequencer.addMetaEventListener(this);
    }
    catch (InvalidMidiDataException ex) {
    }
    catch (MidiUnavailableException e) {
    }

    // Start playing
    thread = new Thread(this);
    thread.start();
  }

  public void stop() {
    if (isPlaying) {
      sequencer.stop();
      isPlaying = false;
    }
    if (thread != null) {
      thread = null;
    }
  }

  public void run() {
    Thread currentThread = Thread.currentThread();
    while (currentThread == thread && !isPlaying) {
      sequencer.start();
      isPlaying = true;
      try {
        thread.sleep(1000l);
      }
      catch (InterruptedException ex) {
      }
    }
  }

  public void meta(MetaMessage event) {
    if (event.getType() == 47) {
      System.out.println("Sequencer is done playing.");
    }
  }
}