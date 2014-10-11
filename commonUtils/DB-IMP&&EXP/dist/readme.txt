1.首先修改./resource/TableList.properties文件
	记录需要到处的表名字，一行一个
2.修改./resource/Info.properties文件
	配置数据库信息

文件说明：
    export.bat：导出TableList.properties里面所有表的数据。
    import.bat：把导出的数据文件导入数据库。
    import-del.bat：导入前会先把表的数据清掉，适合有重复数据的情况。


注意：	
	导入数据前，需要修改配置文件，指向需要导入数据的数据库
	现在支持oracle和mysql，加入其他数据库的驱动，修改bat文件，即可支持别的数据库。
