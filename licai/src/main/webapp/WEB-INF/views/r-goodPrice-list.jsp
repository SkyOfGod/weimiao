<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div style="width: 100%;height: 40px;">
    公司:&nbsp;&nbsp;<input class="easyui-textbox" id="listr_searchCompanyId">
    年份:&nbsp;&nbsp;<input class="easyui-textbox" id="listr_searchYear">
    <button style="margin-left: 10px" class="easyui-linkbutton" iconCls="icon-search"
            onclick="rGoodPricelistSearch()">
        搜索
    </button>
</div>
<table id="goodPrice-list" style="width:100%;height:600px"></table>

<div id="goodPriceEdit" class="easyui-dialog" data-options="closed:true">
    <form id="goodPriceEditForm" method="post">
        <table cellpadding="8">
            <input type="hidden" name="id">
            <tr>
                <td>公司:</td>
                <td>
                    <input class="easyui-textbox" name="name" style="width: 150px;" data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>机构净利润增长率（>1&<100）:</td>
                <td>
                    <input class="easyui-numberbox" name="institutionalGrowthRate" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>合理市盈率:</td>
                <td>
                    <input class="easyui-numberbox" name="earningsRatio" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>总股本（万股）:</td>
                <td>
                    <input class="easyui-numberbox" name="generalCapital" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>备注:</td>
                <td><input class="easyui-textbox" name="remark" data-options="multiline:true,validType:'length[0,150]'"
                           style="height:60px;width: 200px;"/></td>
                </td>
            </tr>
        </table>
    </form>
</div>


<script type="text/javascript" charset="utf-8">

    $("#listr_searchCompanyId").combogrid({
        panelWidth: 260,
        idField: 'id',
        textField: 'name',
        url: '/company/combogrid',
        mode: 'remote',
        delay: 500,
        columns: [[
            {field: 'id', title: '主键', width: 40, align: 'center'},
            {field: 'code', title: '股票代码', width: 100, align: 'center'},
            {field: 'name', title: '公司名称', width: 100, align: 'center'},
        ]],
    });

    $('#goodPrice-list').datagrid({
        url: '/goodPrice/listPage',
        title: '好价格',
        pagePosition: 'top',
        rownumbers: true,
        singleSelect: true,
        collapsible: true,
        pagination: true,
        pageSize: 20,
        pageList: [20, 50, 100],
        toolbar: [
            {
                text: '编辑',
                iconCls: 'icon-edit',
                handler: function () {
                    editGoodPriceData();
                }
            }, '-', {
                text: '删除',
                iconCls: 'icon-cancel',
                handler: function () {
                    deleteGoodPriceData();
                }
            }
        ],
        columns: [[
            {field: 'id', checkbox: true},
            {field: 'code', title: '股票代码', width: 100, align: 'center'},
            {field: 'name', title: '公司名称', width: 100, align: 'center'},
            {field: 'year', title: '年份', width: 40, align: 'center'},
            {field: 'goodPrice', title: '好价格(元)', width: 120, align: 'right',
                formatter: function (value, row, index) {
                    if (value != 0) {
                        return value/100 
                    }
                    return value;
                }},
            {field: 'netProfitGrowthRate', title: '净利润增长率', width: 120, align: 'right',
                formatter: function (value, row, index) {
                    if (value != 0) {
                        return value/100 + '%'
                    }
                    return value;
                }
            },
            {field: 'compoundGrowthRate', title: '净利润复合增长率', width: 120, align: 'right',
                formatter: function (value, row, index) {
                    if (value != 0) {
                        return value/100 + '%'
                    }
                    return value;
                }
            },
            {field: 'institutionalGrowthRate', title: '机构净利润增长率', width: 120, align: 'right',
                formatter: function (value, row, index) {
                    if (value != 0) {
                        return value/100 + '%'
                    }
                    return value;
                }
            },
            {field: 'netProfitNextThreeYears', title: '未来三年的净利润(万元)', width: 150, align: 'right'},
            {field: 'reasonableValuationNextThreeYears', title: '未来三年的合理估值(万元)', width: 150, align: 'right'},
            {field: 'earningsRatio', title: '合理市盈率', width: 150, align: 'right'},
            {field: 'generalCapital', title: '总股本(万股)', width: 150, align: 'right'},
            {field: 'remark', title: '备注', width: 200, align: 'left'},
            {field: 'createTime', title: '创建时间', width: 150, align: 'center'},
            {field: 'updateTime', title: '修改时间', width: 150, align: 'center'},
        ]],
        onBeforeLoad: function (param) {
            param.pageNo = param.page;
            param.pageSize = param.rows;
            param.companyId = $("#listr_searchCompanyId").val();
            param.year = $("#listr_searchYear").val();
            return true;
        }
    });

    rGoodPricelistSearch = function () {
        $('#goodPrice-list').datagrid('load');
    }

    editGoodPriceData = function () {
        var ids = getGoodPriceSelectionsIds();
        if (ids.length === 0) {
            $.messager.alert('提示', '必须选择一条数据才能编辑!');
            return;
        }
        if (ids.indexOf(',') > 0) {
            $.messager.alert('提示', '只能选择一条数据!');
            return;
        }
        $("#goodPriceEdit").dialog({
            title: '编辑好公司指标',
            width: 500,
            height: 400,
            top: 10,
            left: 150,
            closed: false,
            cache: false,
            modal: true,
            buttons: [{
                text: '保存',
                handler: function () {
                    var params = $("#goodPriceEditForm").serialize();
                    $.post("/goodPrice/update", params, function (data) {
                        if (data.code == 200) {
                            $.messager.alert('提示', '修改成功!', 'info',
                                function () {
                                    $("#goodPriceEdit").dialog('close');
                                    $("#goodPrice-list").datagrid("reload");
                                });
                        } else {
                            $.messager.alert('提示', data.msg, 'warning');
                        }
                    });
                }
            }, {
                text: '关闭',
                handler: function () {
                    $("#goodPriceEdit").dialog("close");
                }
            }],
            onBeforeClose: function () {
                $("#goodPriceEditForm").form("clear");
            }
        });
        var data = $("#goodPrice-list").datagrid("getSelected");
        $("#goodPriceEditForm").form("load", data);
    }
    
    deleteGoodPriceData = function () {
        var ids = getGoodPriceSelectionsIds();
        if (ids.length === 0) {
            $.messager.alert('提示', '未选中数据!');
            return;
        }
        $.messager.confirm('确认', '确定删除ID为 ' + ids + ' 公司指标的数据吗？', function (r) {
            if (r) {
                var params = {"id": ids};
                $.post("/goodPrice/delete", params, function (data) {
                    if (data.code == 200) {
                        $.messager.alert('提示', '删除成功!', 'info',
                            function () {
                                $("#goodPrice-list").datagrid("reload");
                            });
                    }
                });
            }
        });
    }

    getGoodPriceSelectionsIds = function () {
        var itemList = $("#goodPrice-list");
        var sels = itemList.datagrid("getSelections");
        var ids = [];
        for (var i in sels) {
            ids.push(sels[i].id);
        }
        ids = ids.join(",");
        return ids;
    }

</script>
