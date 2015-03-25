
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
var path='<%=path%>';
    Ext.onReady(function() {
        Ext.define('User', {
            extend : 'Ext.data.Model',
            fields : [
                    {
                        name : 'id',
                        type : 'int'
                    }, {
                        name : 'name',
                        type : 'string'
                    }, {
                        name : 'address',
                        type : 'string'
                    }, {
                        name : 'mail',
                        type : 'string'
                    }
            ]
        });
        var closeAndLoad=function(_win,_grid){
            if(!_grid){
                _grid=_win;
            }else{
                _win.close();  
            }
            _grid.getStore().load();
            _grid.getSelectionModel().deselectAll();
        }
        var store = Ext.create('Ext.data.JsonStore', {
            autoDestroy : true,
            pageSize : 20,
            proxy : {
                type : 'ajax',
                url : path + '/grid/usersList',
                reader : {
                    type : 'json',
                    root : 'data',
                    totalProperty : 'totalCount'
                },
                actionMethods : {
                    read : "POST"
                }
            },
            autoLoad : true,
            model : 'User'
        });
        var grid = Ext.create('Ext.grid.Panel', {
            dockedItems : [
                {
                    xtype : 'toolbar',
                    dock : 'top',
                    items : [
                            {
                                xtype : 'button',
                                text : '新增',
                                handler : function() {
                                    addwin.show();
                                }
                            }, {
                                xtype : 'button',
                                text : '修改',
                                handler : function() {
                                    var record=grid.getSelectionModel().getSelection();
                                    if(record.length==0){
                                        Ext.Msg.alert('提示', '请选择一条记录!');
                                        return;
                                    }
                                    updatewin(record[0].raw);
                                }
                            }, {
                                xtype : 'button',
                                text : '删除',
                                handler:function(){
                                    var record=grid.getSelectionModel().getSelection();
                                    if(record.length==0){
                                        Ext.Msg.alert('提示', '请选择一条记录!');
                                        return;
                                    }
                                    Ext.Msg.show({
                                        title:'提示!',
                                        msg: '确定删除?',
                                        buttons: Ext.Msg.YESNO,
                                        icon: Ext.Msg.QUESTION,
                                        fn:function(result){
                                            if(result=='yes'){
                                            	Ext.Ajax.request({
                                            	    url: path+'/grid/deleteUser',
                                            	    params: {
                                            	        id: record[0].raw.id
                                            	    },
                                            	    success: function(response){
                                            	        closeAndLoad(grid);
                                            	    },
                                            	    failure: function(t) {
                        					         	Ext.Msg.alert('警告', '操作失败！');
                        					         }
                                            	});
                                            }
                                        }
                                       
                                   });
                                    
                                }
                            }, {
                                xtype : 'button',
                                text : '查询',
                                handler:function(){
                                    var tb=this.up();
                                    var tf=tb.query('textfield');
                                    var store=grid.getStore();
                                    for(var i=0;i<tf.length;i++){
                                        store.getProxy().setExtraParam(tf[i].getName(),tf[i].getValue());
                                    }
                                    store.load();
                                }
                            },{
                                xtype:'textfield',
                                fieldLabel : '名称',
                                labelWidth : 30,
                                name:'name'
                            },{
                                xtype:'textfield',
                                fieldLabel : '地址',
                                labelWidth : 30,
                                name:'address'
                            }
                    ]
                }
            ],
            store : store,
            collapsible : true,
            multiSelect : false,
            columns : [
                    {
                        text : '编号',
                        flex : 1,
                        sortable : false,
                        dataIndex : 'id'
                    }, {
                        text : '名称',
                        flex : 3,
                        dataIndex : 'name'
                    }, {
                        text : '地址',
                        flex : 6,
                        dataIndex : 'address'
                    }, {
                        text : '邮箱',
                        flex : 5,
                        dataIndex : 'mail'
                    }
            ],
            viewConfig : {
                stripeRows : true,
                enableTextSelection : true
            },
            bbar : Ext.create('Ext.PagingToolbar', {
                store : store,
                displayInfo : true,
                displayMsg : '显示第 {0} 条到 {1} 条记录，一共 {2} 条',
                emptyMsg : "没有数据"
            }),
        });
        var addUserForm = Ext.create('Ext.form.Panel', {
            header:false,
            layout : {
                anchor: '100%',
                flex:1
            },
            border : false,
            items : [
                    {
                        xtype:'textfield',
                        fieldLabel : '名称',
                        fieldWidth : 30,
                        msgTarget : 'side',
                        allowBlank : false,
                        name : 'name',
                        flex:1
                    }, {
                        xtype:'textfield',
                        fieldLabel : '地址',
                        fieldWidth : 30,
                        name : 'address',
                        flex:1
                    }, {
                        xtype:'textfield',
                        fieldLabel : '邮箱',
                        fieldWidth : 30,
                        name : 'mail',
                        flex:1,
                        vtype: 'email'
                    }
            ],
            url:path+'/grid/addUser',
            bbar:{
                items:[{
					text:'保存',
					handler:function(){
					    var form=this.up('form');
					    form.submit({
					        success: function(form, action) {
					            Ext.Msg.show({
					                title: '提示',
					                msg: action.result.message,
					                width: 300,
					                buttons: Ext.Msg.OK,
					                fn: function(){
					                    closeAndLoad(addwin,grid);
					                     
					                },
					                icon: Ext.window.MessageBox.INFO
					            });
					         },
					         failure: function(form, action) {
					         	Ext.Msg.alert('警告', action.result.message);
					         }
					    });
					}
         	   	},{
         	   		text:'重置',
					handler:function(){
				    	var form=this.up('form').getForm();
				    	form.reset();
					}  
				}]
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
            items : [
                {
                    region : 'center',
                    collapsible : true,
                    header : false,
                    split : true,
                    width : '100%',
                    layout : 'fit',
                    items : grid
                }
            ]
        })
        var updatewin=function(user){
            Ext.create('widget.window', {
                height : 300,
                width : 600,
                title:'修改',
                modal:true,
                x : document.body.offsetWidth/2-300,
                y : document.body.offsetHeight/2-150,
                closable : true,
                layout : 'border',
                items:[{
                    region : 'center',
                    collapsible : true,
                    header : false,
                    split : true,
                    width : '100%',
                    height:'100%',
                    layout : 'fit',
                    items : Ext.create('Ext.form.Panel', {
                        header:false,
                        layout : {
                            anchor: '100%',
                            flex:1
                        },
                        border : false,
                        items : [{
                            		xtype: 'hiddenfield',
                            		name : 'id',
                            		value:user.id
                        		},{
                                    xtype:'textfield',
                                    fieldLabel : '名称',
                                    fieldWidth : 30,
                                    msgTarget : 'side',
                                    allowBlank : false,
                                    name : 'name',
                                    flex:1,
                                    value:user.name
                                }, {
                                    xtype:'textfield',
                                    fieldLabel : '地址',
                                    fieldWidth : 30,
                                    name : 'address',
                                    flex:1,
                                    value:user.address
                                }, {
                                    xtype:'textfield',
                                    fieldLabel : '邮箱',
                                    fieldWidth : 30,
                                    name : 'mail',
                                    flex:1,
                                    vtype: 'email',
                                    value:user.mail
                                }
                        ],
                        url:path+'/grid/updateUser',
                        bbar:{
                            items:[{
            					text:'保存',
            					handler:function(){
            					    var win=this.up('window')
            					    var form=this.up('form');
            					    form.submit({
            					        success: function(form, action) {
            					            Ext.Msg.show({
            					                title: '提示',
            					                msg: action.result.message,
            					                width: 300,
            					                buttons: Ext.Msg.OK,
            					                fn: function(){
            					                    closeAndLoad(win,grid);
            					                     
            					                },
            					                icon: Ext.window.MessageBox.INFO
            					            });
            					         },
            					         failure: function(form, action) {
            					         	Ext.Msg.alert('警告', action.result.message);
            					         }
            					    });
            					}
                     	   	},{
                     	   		text:'重置',
            					handler:function(){
            				    	var form=this.up('form').getForm();
            				    	form.reset();
            					}  
            				}]
                        }

                    })
                }]
            }).show();
        }
        var addwin = Ext.create('widget.window', {
            height : 300,
            width : 600,
            title:'新增',
            modal:true,
            closable:true,
            closeAction:'hide',
            x : document.body.offsetWidth/2-300,
            y : document.body.offsetHeight/2-150,
            plain : true,
            layout : 'border',
            items:[{
                region : 'center',
                collapsible : true,
                header : false,
                split : true,
                width : '100%',
                height:'100%',
                layout : 'fit',
                items : addUserForm
            }],
            listeners:{
                beforeclose:function(){
                    addUserForm.getForm().reset();
                }
            }
        });
    })
</script>
</head>
<body>
</body>
</html>
