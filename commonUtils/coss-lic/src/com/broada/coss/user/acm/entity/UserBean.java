 package com.broada.coss.user.acm.entity;
 
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import com.broada.acm.authorization.entity.FunctionPermission;
import com.broada.acm.user.entity.User;
import com.broada.component.bizcode.bean.impl.BizCodeBean;
import com.broada.component.bizcode.logic.internal.BizCodeMgrFacade;
import com.broada.coss.user.acm.AuthServiceProvider;
import com.broada.coss.user.acm.UserServiceProvider;
import com.broada.coss.user.acm.logic.LogicManager;
import com.broada.itil.common.AppContextMgr;
import com.broada.itil.rota.logic.impl.RotaFacadeImpl;
 
 public class UserBean
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private User innerUser;
   private String deptName;
   private String orgName;
   private final Map<String, Boolean> roleCheckedCache = new HashMap();
   private List<String> dynaRoles = new ArrayList();
 
   private final Map<String, Boolean> permissionCheckedCache = new HashMap();
   private final List<FunctionPermission> dynaPermission = new ArrayList();
 
   private static final Pattern RES_PERMISSION_PATTERN = Pattern.compile("(.*):(.*):(.*)");
 
   private String currentDomainId = null;
 
   public UserBean(final User user)
   {
     this.innerUser = user;
   }
 
   public UserBean() {
     this.innerUser = new User();
   }
 
   public User getInnerUser() {
     return this.innerUser;
   }
 
   public void setInnerUser(final User innerUser) {
     this.innerUser = innerUser;
   }
 
   public String getId() {
     return this.innerUser.getId();
   }
 
   public String getName() {
     return this.innerUser.getName();
   }
 
   public String getOrgId() {
     return this.innerUser.getOrgId();
   }
 
   public Date getExpiredDate() {
     return this.innerUser.getExpiredDate();
   }
 
   public String getRank() {
     return this.innerUser.getRank();
   }
 
   public int getStatus() {
     return this.innerUser.getStatus();
   }
 
   public String getPki() {
     return this.innerUser.getPki();
   }
 
   public String getDeptId() {
     return this.innerUser.getDeptId();
   }
 
   public boolean isExistForceEditableProps() {
     return AppContextMgr.isTextExist("itil.user.props.forceEditable");
   }
 
   public String getForceEditableProps() {
     return AppContextMgr.getText("itil.user.props.forceEditable");
   }
 
   public String getAccount() {
     return this.innerUser.getAccount();
   }
 
   public String getPassword() {
     return this.innerUser.getPassword();
   }
 
   public String getEmail() {
     return this.innerUser.getEmail();
   }
 
   public String getMobile() {
     return this.innerUser.getMobile();
   }
 
   public String getPhone() {
     return this.innerUser.getPhone();
   }
 
   public String getDeptName() {
     if ((getDeptId() != null) && (this.deptName == null)) {
       this.deptName = UserServiceProvider.getInstance().getUserFacade().getDepartmentById(getDeptId()).getName();
     }
     return this.deptName;
   }
 
   public void setDeptName(final String deptName) {
     this.deptName = deptName;
   }
 
   public String getOrgName() {
     if (this.orgName == null) {
       this.orgName = UserServiceProvider.getInstance().getUserFacade().getOrganizationById(getOrgId()).getName();
     }
     return this.orgName;
   }
 
   public void setOrgName(final String orgName) {
     this.orgName = orgName;
   }
 
   public List<String> getDynaRoles()
   {
     return this.dynaRoles;
   }
 
   public void setDynaRoles(final List<String> dynaRoles) {
     if (dynaRoles == null) {
       return;
     }
     this.dynaRoles = dynaRoles;
     Set set = new HashSet();
     for (Iterator it = dynaRoles.iterator(); it.hasNext(); ) {
       List list = AuthServiceProvider.getInstance().getAuthFacade().getFunctionPermissionsIncludeInheritInRole((String)it.next());
 
       if (list != null) {
         set.addAll(list);
       }
     }
     this.dynaPermission.clear();
     this.dynaPermission.addAll(set);
   }
 
   public boolean hasRoleByRefId(final String roleReferId)
   {
     String roleId = roleReferId;
 
     String domainId = AppContextMgr.getCurrentDomain().getId();
     if (domainId != null) {
       roleId = LogicManager.getInstance().getRoleReferenceLogic().getByKey(domainId, roleReferId).getRoleId();
     }
     return hasRole(roleId);
   }
 
   public boolean hasRole(final String roleId)
   {
     if (roleId == null) {
       return false;
     }
 
     Boolean cacheValue = this.roleCheckedCache.get(roleId);
     if ((cacheValue != null) && (cacheValue.booleanValue())) {
       return true;
     }
 
     boolean hasRole = AuthServiceProvider.getInstance().getAuthFacade().hasRole(getId(), roleId);
     this.roleCheckedCache.put(roleId, Boolean.valueOf(hasRole));
     if (hasRole) {
       return true;
     }
 
     return hasDynaRole(roleId);
   }
 
   private boolean hasDynaRole(final String roleId) {
     return (this.dynaRoles != null) && (this.dynaRoles.contains(roleId));
   }
 
   public boolean checkResGrpPerm(final long resGrpId, final String resAction)
   {
       return true;
//     if (resGrpId == 0L) {
//       return false;
//     }
//     if (StringUtil.isNullOrBlank(resAction)) {
//       resAction = "view";
//     }
//     boolean hasPerm = AuthServiceProvider.getInstance().getAuthFacade().hasResourcePerm(resGrpId, resAction);
//     return hasPerm;
   }
 
   public boolean checkResViewPerm(final String bizId, final String resClassId)
   {
     return true;//checkResourcePermission(bizId, resClassId, "view");
   }
 
   public boolean checkResourcePermission(final String bizId, final String resClassId, final String resAction)
   {
       return true;
//       
//     if ((StringUtil.isNullOrBlank(bizId)) || (StringUtil.isNullOrBlank(resClassId))) {
//       return false;
//     }
// 
//     if (StringUtil.isNullOrBlank(resAction)) {
//       resAction = "view";
//     }
// 
//     String resPermKey = bizId + ":" + resClassId + ":" + resAction;
// 
//     Boolean cacheValue = this.permissionCheckedCache.get(resPermKey);
//     if ((cacheValue != null) && (cacheValue.booleanValue())) {
//       return true;
//     }
// 
//     if ("itsm_bizModule".equals(resClassId)) {
//       String moduleKey = LicenseHelper.getBizModuleLicenseKey(bizId);
// 
//       if (moduleKey != null) {
//         boolean hasLic = false;
//         if (A.CM(moduleKey)) {
//           hasLic = true;
//         }
// 
//         if (!hasLic) {
//           this.permissionCheckedCache.put(resPermKey, Boolean.valueOf(hasLic));
//           return hasLic;
//         }
//       }
//     }
// 
//     boolean hasPerm = false;
// 
//     if ("itsm_kb_knownledgeType".equals(resClassId))
//       hasPerm = checkKbTypePM(bizId);
//     else {
//       hasPerm = AuthServiceProvider.getInstance().getAuthFacade().hasResPerm(bizId, resClassId, resAction);
//     }
//     this.permissionCheckedCache.put(resPermKey, Boolean.valueOf(hasPerm));
//     if (hasPerm) {
//       return true;
//     }
//     return checkDynaResPerm(bizId, resClassId, resAction);
   }
 
   private boolean checkKbTypePM(final String bizId)
   {
//     ResourceFacade resFacade = ResServiceProvider.getInstance().getResFacade();
//     ResourceInstance res = resFacade.getResourceInstanceByBizId("itsm_kb_knownledgeType", bizId);
//     boolean hasPerm = AuthServiceProvider.getInstance().getAuthFacade().hasResourcePerm(res.getId(), "view");
//     if (hasPerm) return true;
//     ResourceInstance ri = new ResourceInstance();
//     ri.setParentId(res.getId());
//     List<ResourceInstance> instances = resFacade.queryResInstances(ri);
//     if ((instances != null) && (!instances.isEmpty())) {
//       for (ResourceInstance resInstance : instances) {
//         hasPerm = AuthServiceProvider.getInstance().getAuthFacade().hasResourcePerm(resInstance.getId(), "view");
//         if (hasPerm) {
//           return true;
//         }
//         if (checkKbTypePM(resInstance.getBizId())) {
//           return true;
//         }
//       }
//     }
//     return false;
       return true;
   }
 
   private boolean checkDynaResPerm(final String bizId, final String resClassId, final String resAction) {
       return true;/*
     if ((this.dynaRoles == null) || (this.dynaRoles.isEmpty())) {
       return false;
     }
 
     ResourceInstance res = ResServiceProvider.getInstance().getResFacade().getResourceInstanceByBizId(resClassId, bizId);
 
     if (res == null) {
       return true;
     }
 
     if ((AppContextMgr.getCurrentDomain() == null) || (AppContextMgr.getCurrentDomain().getId() == null)) {
       return false;
     }
 
     List<ResourceAclEntry> entries = AuthServiceProvider.getInstance().getAuthFacade().getResourceAcl(AppContextMgr.getCurrentDomain().getId(), res.getId());
 
     for (ResourceAclEntry entry : entries)
     {
       if ((entry.getPermission() == null) || (entry.getPermission().getResourceAction() == null) || (!resAction.equals(entry.getPermission().getResourceAction().getAction())))
       {
         continue;
       }
 
       if (this.dynaRoles.contains(entry.getPrincipalId())) {
         return true;
       }
     }
 
     return false;
     */
   }
 
   public boolean checkFunctionPermission(final String funcPermId)
   {
       return true;
       /*
     if (StringUtil.isNullOrBlank(funcPermId)) {
       return false;
     }
 
     Boolean cacheValue = this.permissionCheckedCache.get(funcPermId);
     if ((cacheValue != null) && (cacheValue.booleanValue())) {
       return true;
     }
 
     boolean hasPerm = AuthServiceProvider.getInstance().getAuthFacade().hasFuncPerm(funcPermId);
     this.permissionCheckedCache.put(funcPermId, Boolean.valueOf(hasPerm));
     if (hasPerm) {
       return true;
     }
 
     return hasDynaPermission(funcPermId);
     */
   }
 
   private boolean hasDynaPermission(final String funcPermId) {
       return true;
       /*
     if (StringUtil.isNullOrBlank(funcPermId)) {
       return false;
     }
     FunctionPermission perm = new FunctionPermission();
     perm.setId(funcPermId);
     return (this.dynaPermission != null) && (this.dynaPermission.contains(perm));
     */
   }
 
   public boolean checkPermissions(final String permisionIds)
   {
       return true;
       /*
     String[] ids = permisionIds.split(",");
     for (int i = 0; i < ids.length; i++) {
       if (checkPermission(ids[i]))
         return true;
     }
     return false;
     */
   }
 
   public boolean checkPermission(final String permisionId)
   {
       return true;
       /*
     Boolean cacheValue = this.permissionCheckedCache.get(permisionId);
     if ((cacheValue != null) && (cacheValue.booleanValue())) {
       return true;
     }
 
     Matcher matcher = RES_PERMISSION_PATTERN.matcher(permisionId);
     if ((matcher.matches()) && (matcher.groupCount() == 3)) {
       return checkResourcePermission(matcher.group(1), matcher.group(2), matcher.group(3));
     }
     return checkFunctionPermission(permisionId);
     */
   }
 
   public boolean isDomainManager()
   {
       return true;
       /*
     if ((AppContextMgr.getCurrentDomain() == null) || (AppContextMgr.getCurrentUser() == null))
       return false;
     String domainId = AppContextMgr.getCurrentDomain().getId();
     String userId = AppContextMgr.getCurrentUser().getId();
 
     return (CommonUtil.isDomainAdmin(domainId, userId)) || (CommonUtil.isDomainSecMan(domainId, userId)) || (CommonUtil.isDomainAuditor(domainId, userId));
     */
   }
 
   public boolean isDomainSecMan()
   {
       return true;
       /*
     if ((AppContextMgr.getCurrentDomain() == null) || (AppContextMgr.getCurrentUser() == null)) {
       return false;
     }
     return CommonUtil.isDomainSecMan(AppContextMgr.getCurrentDomain().getId(), AppContextMgr.getCurrentUser().getId());
     */
   }
 
   public boolean isDomainAuditor()
   {
       return true;
   }
//     if ((AppContextMgr.getCurrentDomain() == null) || (AppContextMgr.getCurrentUser() == null)) {
//       return false;
//     }
//     return CommonUtil.isDomainAuditor(AppContextMgr.getCurrentDomain().getId(), AppContextMgr.getCurrentUser().getId());
//   }
 
   public boolean isDomainAdmin()
   {
       return true;
//     if ((AppContextMgr.getCurrentDomain() == null) || (AppContextMgr.getCurrentUser() == null)) {
//       return false;
//     }
//     return CommonUtil.isDomainAdmin(AppContextMgr.getCurrentDomain().getId(), AppContextMgr.getCurrentUser().getId());
   }
 
   public boolean isCurrentRotaUser()
   {
     BizCodeMgrFacade bizCodeMgrFacade = (BizCodeMgrFacade)AppContextMgr.getAppContext().getBean("codeManager");
     BizCodeBean bizCodeBean = new BizCodeBean();
     bizCodeBean.setDsBusinessName("rota_category");
 
     List<BizCodeBean> rotaTypes = bizCodeMgrFacade.getAllChildsBizCodes(bizCodeBean);
     if ((rotaTypes == null) || (rotaTypes.isEmpty())) {
       return false;
     }
 
     RotaFacadeImpl rf = RotaFacadeImpl.getInstance();
     for (BizCodeBean rotaType : rotaTypes) {
       if (rf.isWatcher(getId(), rotaType.getId())) {
         return true;
       }
     }
     return false;
   }
 
   public void clearCache()
   {
     this.permissionCheckedCache.clear();
     this.roleCheckedCache.clear();
   }
 
   public String getCurrentDomainId()
   {
     return this.currentDomainId;
   }
 
   public void setCurrentDomainId(final String currentDomainId) {
     this.currentDomainId = currentDomainId;
   }
 
   @Override
public boolean equals(final Object obj)
   {
     if (!(obj instanceof UserBean)) {
       return false;
     }
     UserBean ub = (UserBean)obj;
     if ((getId() != null) && (ub.getId() != null) && (getId().equals(ub.getId()))) {
       return true;
     }
     return (getAccount() != null) && (ub.getAccount() != null) && (getAccount().equals(ub.getAccount()));
   }
 
   @Override
public int hashCode()
   {
     if (getId() != null) {
       return getId().hashCode();
     }
     if (getAccount() != null) {
       return getAccount().hashCode();
     }
     return super.hashCode();
   }
 }
