<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
    String basePath = request.getContextPath();
			String path = request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort() + basePath;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="<%=path%>/resources/js/ext4.2/resources/css/ext-all-neptune.css" />
<script type="text/javascript"
	src="<%=path%>/resources/js/ext4.2/bootstrap.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/ext4.2/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript">
var path="<%=path%>";
	Ext.onReady(function() {
		var store = Ext.create('Ext.data.TreeStore', {
			proxy : {
				type : 'ajax',
				url : path + '/getMenus',
				reader : {
					type : 'json'
				},
				actionMethods : {
					read : "POST"
				},
			},
			defaultRootId : null,
			nodeParam : 'id',
			autoLoad : true
		});
		var tabs=[];
		var containTab=function(c,nodeid){
			for(var i=0;i<c.length;i++){
				if(c[i].nodeid===nodeid){
					return i;
				}
			}
			return -1;
			
		}
		var removeTab=function(nodeid){
			for(var i=0;i<tabs.length;i++){
				if(tabs[i].nodeid===nodeid){
					tabs.remove(i);
				}
			}
		}
		var createTab=function(node){
			var name=node.text;
			var nodeid=node.id;
			var url=node.url;
			var t=Ext.create('Ext.panel.Panel',{
				nodeid:nodeid,
				title:name,
				layout:'fit',
                closable:'true',
                closeAction:'hide',
                html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+path+url+'"> </iframe>'
			})
			tabs.push(t);
			return t;
		}
		var menutree = Ext.create('Ext.tree.Panel', {
			title : '功能菜单',
			width : '100%',
			height : '100%',
			store : store,
			rootVisible : false,
			listeners : {
				itemclick : function(tree, record, item, index, e, eOpts) {
					var node=record.raw;
					if(node.leaf){
						var tabpanel=tree.up('viewport').down('tabpanel');
						var index=-1;
						var tab;
						if((index=containTab(tabpanel.items.items,node.id))!=-1){
							
							tab=tabs[index];
						}else{
							tab=createTab(node);
							tabpanel.add(tab);
						}
						
						tab.focus(false);
						tab.show();
					}
					
				}
			}

		});
		Ext.create('Ext.Viewport', {
			layout : {
				type : 'border',
				padding : 5
			},
			defaults : {
				split : true
			},
			items : [ {
				region : 'west',
				collapsible : true,
				header : false,
				split : true,
				width : '15%',
				minWidth : 100,
				layout : 'fit',
				items : menutree
			}, {
				region : 'north',
				collapsible : true,
				title : 'North',
				split : true,
				height : 100,
				minHeight : 60,
				html : 'north'
			}, {
				xtype : 'tabpanel',
				region : 'center',
				header : false,
				collapsible : true,
			} ]
		})

	})
</script>
<title>首页</title>
</head>
<body>
</body>
</html>