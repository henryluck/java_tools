/*    */ package org.zeroturnaround.jrebel.mybatis;
/*    */ 
/*    */ import java.net.URL;
/*    */ import java.util.Collection;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ public class ResourceContext
/*    */ {
/*  9 */   private static final ThreadLocal<Collection<URL>> CONTEXT = new ThreadLocal();
/*    */ 
/*    */   public static void enter()
/*    */   {
/* 13 */     CONTEXT.set(new HashSet());
/*    */   }
/*    */ 
/*    */   public static Collection<URL> exit()
/*    */   {
/* 18 */     Collection resources = (Collection)CONTEXT.get();
/* 19 */     CONTEXT.remove();
/* 20 */     return resources;
/*    */   }
/*    */ 
/*    */   public static void addUrl(URL url) {
/* 24 */     Collection resources = (Collection)CONTEXT.get();
/* 25 */     if (resources == null) {
/* 26 */       return;
/*    */     }
/* 28 */     resources.add(url);
/*    */   }
/*    */ }

/* Location:           F:\eclipse-jee-juno-SR2-win32\plugins\org.zeroturnaround.eclipse.embedder_5.3.1.RELEASE-201307081557\jrebel\mybatis-jr-plugin-5.6.3a.jar
 * Qualified Name:     org.zeroturnaround.jrebel.mybatis.ResourceContext
 * JD-Core Version:    0.6.0
 */