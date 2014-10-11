package jlx.tools.refreshetao;

import jlx.util.hotkey.IHotKeyAction;

import com.melloware.jintellitype.JIntellitype;

public class HotKeyHandler implements IHotKeyAction {
    
    private final MainFrame mainFrame;
    
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
    public void hotKeyAction() {
        mainFrame.setVisible(!mainFrame.isVisible());
    }
}
