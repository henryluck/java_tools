package jlx.tools.research.vo;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * {class description}
 * <br>
 *  
 * <p>
 * Create on : 2012-6-1<br>
 * <p>
 * </p>
 * <br>
 * @author henry.luck@gmail.com<br>
 * @version CompanyResearchTool v1.0
 * <p>
 *<br>
 * <strong>Modify History:</strong><br>
 * user     modify_date    modify_content<br>
 * -------------------------------------------<br>
 * <br>
 */
public class CompanyInfo {
    public CompanyInfo(){
        
    }
    
    public CompanyInfo(String name){
        this.name = name;
    }
    
    /**
     * <code>name</code> - {销售负责人}.
     */
    private String ownerUser;
    
    /**
     * <code>name</code> - {销售负责人部门}.
     */
    private String ownerDep;

    /**
     * <code>name</code> - {公司名称}.
     */
    private String name;
    /**
     * <code>address</code> - {地址}.
     */
    private String address;
    /**
     * <code>email</code> - {邮箱地址}.
     */
    private String email;
    /**
     * <code>qq</code> - {qq号}.
     */
    private String qq;
    /**
     * <code>contactPerson</code> - {联系人}.
     */
    private String contactPerson;
    /**
     * <code>url</code> - {公司网站}.
     */
    private String url;
    /**
     * <code>name</code> - {邮政编码}.
     */
    private String zipCode;
    /**
     * <code>business</code> - {公司行业}.
     */
    private String industry;
    /**
     * <code>size</code> - {公司规模}.
     */
    private String size;
    
    /**
     * <code>complex</code> - {复杂数据，适用于公司信息格式分不开的情况，都放这个里面}.
     */
    private String complex;
    
    /**
     * <code>detailURL</code> - {公司详细信息的url}.
     */
    private String detailURL;
    /**
     * @return name - {return content description}
     */
    public String getName() {
        return name;
    }
    /**
     * @param name - {parameter description}.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return address - {return content description}
     */
    public String getAddress() {
        return address;
    }
    /**
     * @param address - {parameter description}.
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * @return email - {return content description}
     */
    public String getEmail() {
        return email;
    }
    /**
     * @param email - {parameter description}.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return qq - {return content description}
     */
    public String getQq() {
        return qq;
    }
    /**
     * @param qq - {parameter description}.
     */
    public void setQq(String qq) {
        this.qq = qq;
    }
    /**
     * @return contactPerson - {return content description}
     */
    public String getContactPerson() {
        return contactPerson;
    }
    /**
     * @param contactPerson - {parameter description}.
     */
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
    /**
     * @return url - {return content description}
     */
    public String getUrl() {
        return url;
    }
    /**
     * @param url - {parameter description}.
     */
    public void setUrl(String url) {
        this.url = url;
    }
    /**
     * @return zipCode - {return content description}
     */
    public String getZipCode() {
        return zipCode;
    }
    /**
     * @param zipCode - {parameter description}.
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    /**
     * @return industry - {return content description}
     */
    public String getIndustry() {
        return industry;
    }
    /**
     * @param industry - {parameter description}.
     */
    public void setIndustry(String industry) {
        this.industry = industry;
    }
    /**
     * @return size - {return content description}
     */
    public String getSize() {
        return size;
    }
    /**
     * @param size - {parameter description}.
     */
    public void setSize(String size) {
        this.size = size;
    }
    /**
     * @return complex - {return content description}
     */
    public String getComplex() {
        return complex;
    }
    /**
     * @param complex - {parameter description}.
     */
    public void setComplex(String complex) {
        this.complex = complex;
    }
    /**
     * @return detailURL - {return content description}
     */
    public String getDetailURL() {
        return detailURL;
    }
    /**
     * @param detailURL - {parameter description}.
     */
    public void setDetailURL(String detailURL) {
        this.detailURL = detailURL;
    }
    /**
     * @return ownerUser - {return content description}
     */
    public String getOwnerUser() {
        return ownerUser;
    }
    /**
     * @param ownerUser - {parameter description}.
     */
    public void setOwnerUser(String ownerUser) {
        this.ownerUser = ownerUser;
    }
    /**
     * @return ownerDep - {return content description}
     */
    public String getOwnerDep() {
        return ownerDep;
    }
    /**
     * @param ownerDep - {parameter description}.
     */
    public void setOwnerDep(String ownerDep) {
        this.ownerDep = ownerDep;
    }
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
    /* (non-Javadoc) 为了collection使用contains方法比较使用
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof CompanyInfo ){
            CompanyInfo info = (CompanyInfo)obj;
            if(obj !=null && info.getName().equals(this.getName())){
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
