<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>财务报表管理系统</title>
<jsp:include page="/commons/common-js.jsp"></jsp:include>
<style type="text/css">
	.content {
		padding: 10px 10px 10px 10px;
	}
</style>
</head>
<body class="easyui-layout">
    <div data-options="region:'west',title:'菜单',split:true" style="width:220px;">
    	<ul id="menu" class="easyui-tree" style="margin-top: 10px;margin-left: 5px;">
         	<li>
         		<span>公司数据管理</span>
         		<ul>
					<li data-options="attributes:{'url':'/page/company-list'}">公司-详情</li>
					<li data-options="attributes:{'url':'/page/combine-consolidated-assets-liabilities-list'}">公司-合并资产负债表</li>
					<li data-options="attributes:{'url':'/page/combine-profit-list'}">公司-合并利润表</li>
					<li data-options="attributes:{'url':'/page/combine-cash-flow-list'}">公司-合并现金流量表</li>
					<%--<li data-options="attributes:{'url':'/page/item-img-list'}">查询商品图片</li>
					<li data-options="attributes:{'url':'/page/item-img-category-list'}">查询商品图片类目</li>--%>
	         	</ul>
         	</li>
         	<li>
         		<span>统计指标管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'/page/liabilities-statistics-list'}">合并资产负债表指标</li>
	         		<li data-options="attributes:{'url':'/page/profit-statistics-list'}">合并利润表指标</li>
					<li data-options="attributes:{'url':'/page/cash-flow-statistics-list'}">合并现金流量表指标</li>
					<li data-options="attributes:{'url':'/page/important-target-list'}">重要指标</li>
	         	</ul>
         	</li>
         	<li>
         		<span>配置管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'/page/target-category-list'}">指标配置</li>
	         		<%--<li data-options="attributes:{'url':'/page/user'}">查询用户</li>--%>
	         	</ul>
         	</li>
			<li>
                <span>热点管理</span>
                <ul>
                    <li data-options="attributes:{'url':'/page/hot-type-list'}">查询热点类型</li>
                    <%--<li data-options="attributes:{'url':'/page/rule-item'}">查询规则-商品</li>--%>
                </ul>
            </li>
         </ul>
    </div>
    <div data-options="region:'center',title:''">
    	<div id="tabs" class="easyui-tabs">
		    <div title="首页" style="padding:20px;">
		        	
		    </div>
		</div>
    </div>
    
<script type="text/javascript">
$(function(){
	$('#menu').tree({
		onClick: function(node){
			if($('#menu').tree("isLeaf",node.target)){
				var tabs = $("#tabs");
				var tab = tabs.tabs("getTab",node.text);
				if(tab){
					tabs.tabs("select",node.text);
				}else{
					tabs.tabs('add',{
					    title:node.text,
					    href: node.attributes.url,
					    closable:true,
					    bodyCls:"content"
					});
				}
			}
		}
	});
});
</script>
</body>
</html>