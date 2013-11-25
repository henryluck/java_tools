package jlx.tools.research;

import jlx.util.hotkey.IHotKeyAction;

import com.melloware.jintellitype.JIntellitype;

public class HotKeyHandler implements IHotKeyAction {
    
    private final MainFrame mainFrame;
    

    /**
     * <code>ownWindowVisble</code> - {记录我的列表窗口的visible状态，再次点击快捷键的时候恢复使用}.
     */
    private boolean ownWindowVisible;
    
    /**
     * <code>ownWindowVisble</code> - {记录列表窗口的visible状态，再次点击快捷键的时候恢复使用}.
     */
    private boolean nullWindowVisible;
    
    public HotKeyHandler(final MainFrame mainFrame){
        this.mainFrame = mainFrame;
    }

    @Override
    public int getIdentifier() {
        return 1;
    }

    @Override
    public int getModifier() {
        return JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT;
    }

    @Override
    public int getKeycode() {
        return 'I';
    }
    
    @Override
    public void hotKeyAction(){
        if(mainFrame.isVisible()){
            ownWindowVisible = mainFrame.ownFrame.isVisible();
            nullWindowVisible = mainFrame.nullFrame.isVisible();
            
            mainFrame.setVisible(false);
            mainFrame.ownFrame.setVisible(false);
            mainFrame.nullFrame.setVisible(false);
        }else{
            mainFrame.setVisible(true);
            mainFrame.ownFrame.setVisible(ownWindowVisible);
            mainFrame.nullFrame.setVisible(nullWindowVisible);
        }
        
    }
}
