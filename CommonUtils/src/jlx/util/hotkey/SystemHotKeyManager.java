package jlx.util.hotkey;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;

public class SystemHotKeyManager implements HotkeyListener {

    /**
     * <code>KeyCache</code> - 热键id和action的对应关系
     */
    private static final Map<String, IHotKeyAction> KeyCache = new HashMap<String, IHotKeyAction>();

    /**
     * 该方法负责监听注册的系统热键事件
     * 
     * @param key:触发的热键标识
     */
    @Override
    public void onHotKey(final int key) {
        Set<String> keys = KeyCache.keySet();

        for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
            String k = iterator.next();
            if (Integer.parseInt(k) == key) {
                KeyCache.get(k).hotKeyAction();
            }
        }

    }

    /**
     * 解除注册并退出
     */
    void destroy() {
        Set<String> keys = KeyCache.keySet();
        for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
            String k = iterator.next();
            JIntellitype.getInstance().unregisterHotKey(Integer.parseInt(k));
        }
        System.exit(0);
    }

    /**
     * 初始化热键并注册监听事件
     * 
     * @param jFrame
     */
    public void addHotkey(final IHotKeyAction hotkeyAction) {
        // 参数KEY_1表示改组热键组合的标识，第二个参数表示组合键，如果没有则为0，该热键对应ctrl+alt+I
        JIntellitype.getInstance().registerHotKey(hotkeyAction.getIdentifier(), hotkeyAction.getModifier(),
            hotkeyAction.getKeycode());
        // JIntellitype.getInstance().registerHotKey(KEY_2, JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT,
        // (int) 'O');
        // JIntellitype.getInstance().registerHotKey(KEY_3, JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT,
        // (int) 'X');

        KeyCache.put(String.valueOf(hotkeyAction.getIdentifier()), hotkeyAction);
        JIntellitype.getInstance().addHotKeyListener(this);
    }

    public static void main(final String[] args) {
        SystemHotKeyManager key = new SystemHotKeyManager();
        HotkeyTest test = new SystemHotKeyManager.HotkeyTest();
        key.addHotkey(test);

        // 下面模拟长时间执行的任务 
        while (true) {
            try {
                Thread.sleep(10000);
            } catch (Exception ex) {
                break;
            }
        }
    }
    // 测试
   public static  class HotkeyTest implements IHotKeyAction {

        @Override
        public int getIdentifier() {
            return 1;
        }

        @Override
        public int getModifier() {
            return JIntellitype.MOD_CONTROL + JIntellitype.MOD_SHIFT;
        }

        @Override
        public int getKeycode() {
            return 'J';
        }

        @Override
        public void hotKeyAction() {
            System.out.println("action ...");

        }

    }

}
