/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadmp3;

/**
 *
 * @author oneleaf
 */
public class DownThread implements Runnable {

    TaskInfo taskInfo;
    DownTask task;

    public void run() {
        task.downloadmp3(taskInfo);
    }
}
