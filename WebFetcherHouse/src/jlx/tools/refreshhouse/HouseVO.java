package jlx.tools.refreshhouse;

import java.awt.image.BufferedImage;

public class HouseVO {

    public String title;
    public String content;
    public String url;
    public String price;
    public String priceAll;
    public BufferedImage image;
    public String imageUrl;
    public String mianji;
    /* (non-Javadoc) 为了collection使用contains方法比较使用
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if(obj instanceof HouseVO ){
            HouseVO gs = (HouseVO)obj;
            if(obj !=null && gs.url.equals(this.url)){
                return true;
            }
        }
        return false;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

}
