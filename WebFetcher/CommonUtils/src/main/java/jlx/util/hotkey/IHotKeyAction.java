package jlx.util.hotkey;


/**
 * 定义一个热键触发条件，和触发的动作
 * <br>
 *  
 * <p>
 * Create on : 2013-11-21<br>
 * <br>
 */
public interface IHotKeyAction {
    
    /**
     * 热键id
     * @return
     */
    int getIdentifier();
    
    /**
     * 得到辅助的按键，JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT 之类的
     * @return
     */
    int getModifier();
    
    /**
     * 热键字符,例如： 'J'  注：要用大写
     * @return
     */
    int getKeycode();
    
    /**
     * 触发定义的热键时候，回调函数
     */
    void hotKeyAction();
    
    
}
