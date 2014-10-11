/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadmp3;

import org.blinkenlights.jid3.MP3File;
import org.blinkenlights.jid3.io.TextEncoding;
import org.blinkenlights.jid3.v2.ID3V2_3_0Tag;

/**
 *
 * @author oneleaf
 */
public class Mp3Tag {

    static {
        TextEncoding.setDefaultTextEncoding(TextEncoding.UNICODE);
    }

    /**
     * 依据mp3info修改mp3的tag信息
     */
    static void convert(Mp3Info mp3Info) throws Exception {
        MP3File mp3 = new MP3File(mp3Info.saveFile);
        if (mp3Info.album == null) {
            mp3Info.album = "";
        }
        if (mp3Info.title == null) {
            mp3Info.title = "";
        }
        if (mp3Info.artist == null) {
            mp3Info.artist = "";
        }
        ID3V2_3_0Tag oID3V2_3_0Tag = new ID3V2_3_0Tag();
        oID3V2_3_0Tag.setAlbum(mp3Info.album);  // sets TALB frame
        oID3V2_3_0Tag.setArtist(mp3Info.artist);  // sets TPE1 frame
        oID3V2_3_0Tag.setComment("Create by optool.cn");  // sets COMM frame with language "eng" and no description
        oID3V2_3_0Tag.setTitle(mp3Info.title);  // sets TIT2 frame

        // set this v2.3.0 tag in the media file object
        mp3.setID3Tag(oID3V2_3_0Tag);
        mp3.removeID3V1Tag();
        mp3.sync();
    }
}
