<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div style="width: 100%;height: 40px">
    名称:&nbsp;&nbsp;<input class="easyui-textbox" id="listi_searchName">
    日期:&nbsp;&nbsp;<input class="easyui-textbox" id="listi_searchDataDate">
    <button class="easyui-linkbutton" iconCls="icon-search" onclick="hotTypeListSearch()">搜索</button>
</div>
<table id="hot-type-list" style="width:100%;height:1120px"></table>

<div id="editHotType" class="easyui-dialog" data-options="closed:true">
    <form id="editHotTypeForm" method="post">
        <table cellpadding="5">
            <input type="hidden" name="id">
            <tr>
                <td>名称:</td>
                <td>
                    <input class="easyui-textbox" name="name" style="width: 300px;"
                           data-options="editable:true,required:true"/>
                </td>
            </tr>
            <tr>
                <td>排序:</td>
                <td>
                    <input class="easyui-textbox" name="sort" value="0" style="width: 300px;"
                           data-options="editable:true,required:true"/>
                </td>
            </tr>
            <tr>
                <td>状态:</td>
                <td>
                    <select class="easyui-combobox" name="state" style="width: 150px;" data-options="editable:false,required:true">
                        <option value="1">启用</option>
                        <option value="0">关闭</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>备注:</td>
                <td><input class="easyui-textbox" name="remark" data-options="multiline:true,validType:'length[0,150]'"
                           style="height:60px;width: 300px;"/></td>
            </tr>
        </table>
    </form>
</div>

<script type="text/javascript" charset="utf-8">

    $("#listi_searchDataDate").combogrid({
        panelWidth: 240,
        idField: 'key',
        textField: 'key',
        url: '/hotCompanyData/dataDateCombogrid',
        mode: 'remote',
        delay: 500,
        columns: [[
            {field: 'sort', title: '近?天', width: 50, align: 'center'},
            {field: 'key', title: '日期', width: 110, align: 'center'},
            {field: 'value', title: '星期', width: 50, align: 'center'}
        ]],
    });

    $('#hot-type-list').datagrid({
        url: '/hotType/listPage',
        title: '热点类型列表',
        pagePosition: 'top',
        singleSelect: true,
        rownumbers: true,
        collapsible: true,
        pagination: true,
        pageSize: 50,
        pageList: [30, 50, 100],
        toolbar: [{
            text: '新增',
            iconCls: 'icon-add',
            handler: function () {
                addHotType();
            }
        }, '-', {
            text: '编辑',
            iconCls: 'icon-edit',
            handler: function () {
                editHotType();
            }
        }, '-', {
                text: '删除',
                iconCls: 'icon-cancel',
                handler: function () {
                    deleteHotType();
                }
            }
        ],
        columns: [[
            {field: 'id', checkbox: true},
            {field: 'name', title: '名称', width: 120, align: 'center'},
            {field: 'count1', title: '近1天', width: 100, align: 'center'},
            {field: 'count2', title: '近2天', width: 100, align: 'center'},
            {field: 'count3', title: '近3天', width: 100, align: 'center'},
            {field: 'count4', title: '近4天', width: 100, align: 'center'},
            {field: 'count5', title: '近5天', width: 100, align: 'center'},
            {field: 'count6', title: '近6天', width: 100, align: 'center'},
            {field: 'count7', title: '近7天', width: 100, align: 'center'},
            {field: 'count8', title: '近8天', width: 100, align: 'center'},
            {field: 'count9', title: '近9天', width: 100, align: 'center'},
            {field: 'count10', title: '近10天', width: 100, align: 'center'},
            {field: 'count11', title: '近11天', width: 100, align: 'center'},
            {field: 'count12', title: '近12天', width: 100, align: 'center'},
            {field: 'count13', title: '近13天', width: 100, align: 'center'},
            {field: 'count14', title: '近14天', width: 100, align: 'center'},
            {field: 'count15', title: '近15天', width: 100, align: 'center'},
            {field: 'count16', title: '近16天', width: 100, align: 'center'},
            {field: 'count17', title: '近17天', width: 100, align: 'center'},
            {field: 'count18', title: '近18天', width: 100, align: 'center'},
            {field: 'count19', title: '近19天', width: 100, align: 'center'},
            {field: 'count20', title: '近20天', width: 100, align: 'center'},
            {field: 'sort', title: '排序', width: 80, align: 'center'},
            {field: 'state', title: '状态', width: 80, align: 'center',
              formatter: function (value, row, index) {
                if (value === 0) {
                  return '关闭';
                } else if (value === 1) {
                  return '启用';
                }
                return value;
              }
            },
            {field: 'remark', title: '备注', width: 500, align: 'left',
                formatter: function (value, row, index) {
                    return '<span title="' + value + '">' + value + '</span>';
                }
            },
            {field: 'createTime', title: '创建时间', width: 150, align: 'center'},
            {field: 'updateTime', title: '修改时间', width: 150, align: 'center'},
        ]],
        onBeforeLoad: function (param) {
            param.pageNo = param.page;
            param.pageSize = param.rows;
            param.name = $('#listi_searchName').val();
            param.dataDate = $('#listi_searchDataDate').val();
            return true;
        }
    });

    addHotType = function () {
        $("#editHotType").dialog({
            title: '新增指标',
            width: 500,
            height: 500,
            top: 70,
            left: 150,
            closed: false,
            cache: false,
            modal: true,
            buttons: [{
                text: '保存',
                width: 450,
                height: 50,
                handler: function () {
                    if (!$("#editHotTypeForm").form('validate')) {
                        return false;
                    }
                    var params = $("#editHotTypeForm").serialize();
                    $.post("/hotType/add", params, function (data) {
                        if (data.code == 200) {
                            $.messager.alert('提示', '新增成功!', 'info',
                                function () {
                                    $("#editHotType").dialog('close');
                                    $("#hot-type-list").datagrid("reload");
                                });
                        } else {
                            $.messager.alert('提示', data.msg, 'warning');
                        }
                    });
                }
            }/*, {
                text: '关闭',
                handler: function () {
                    $("#editHotType").dialog("close");
                }
            }*/],
            /*onBeforeClose: function () {
                $("#editHotTypeForm").form("clear");
            }*/
        });
    }

    editHotType = function () {
        var ids = getHotTypeListSelectionsIds();
        if (ids.length == 0) {
            $.messager.alert('提示', '必须选择一条数据才能编辑!');
            return;
        }
        if (ids.indexOf(',') > 0) {
            $.messager.alert('提示', '只能选择一条数据!');
            return;
        }
        $("#editHotType").dialog({
            title: '编辑指标',
            width: 500,
            height: 500,
            top: 70,
            left: 150,
            closed: false,
            cache: false,
            modal: true,
            buttons: [{
                text: '保存',
                width: 450,
                height: 50,
                handler: function () {
                    if (!$("#editHotTypeForm").form('validate')) {
                        return false;
                    }
                    var params = $("#editHotTypeForm").serialize();
                    $.post("/hotType/update", params, function (data) {
                        if (data.code == 200) {
                            $.messager.alert('提示', '修改成功!', 'info',
                                function () {
                                    $("#editHotType").dialog('close');
                                    $("#hot-type-list").datagrid("reload");
                                });
                        } else {
                            $.messager.alert('提示', data.msg, 'warning');
                        }
                    });
                }
            }/*, {
                text: '关闭',
                handler: function () {
                    $("#editHotType").dialog("close");
                }
            }*/],
            /*onBeforeClose: function () {
                $("#editHotTypeForm").form("clear");
            }*/
        });
        var data = $("#hot-type-list").datagrid("getSelected");
        $("#editHotTypeForm").form("load", data);
    }

    deleteHotType = function () {
        var ids = getHotTypeListSelectionsIds();
        if (ids.length == 0) {
            $.messager.alert('提示', '未选中数据!');
            return;
        }
        $.messager.confirm('确认', '确定删除ID为 ' + ids + ' 的数据吗？', function (r) {
            if (r) {
                var params = {"id": ids};
                $.post("/hotType/delete", params, function (data) {
                    if (data.code == 200) {
                        $.messager.alert('提示', '删除成功!', 'info',
                            function () {
                                $("#hot-type-list").datagrid("reload");
                            });
                    }
                });
            }
        });
    }

    hotTypeListSearch = function () {
        $('#hot-type-list').datagrid('load');
    }

    getHotTypeListSelectionsIds = function () {
        var itemList = $("#hot-type-list");
        var sels = itemList.datagrid("getSelections");
        var ids = [];
        for (var i in sels) {
            ids.push(sels[i].id);
        }
        ids = ids.join(",");
        return ids;
    }

</script>
