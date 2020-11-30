<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div style="width: 100%;height: 40px">
    指标级别:&nbsp;&nbsp;<input class="easyui-numberbox" id="listg_searchLevel">
    <button class="easyui-linkbutton" iconCls="icon-search" onclick="targetListSearch()">搜索</button>
</div>
<table id="target-list" style="width:100%;height:600px"></table>

<div id="editTarget" class="easyui-dialog" data-options="closed:true">
    <form id="editTargetForm" method="post">
        <table cellpadding="5">
            <input type="hidden" name="id">
            <tr>
                <td>名称:</td>
                <td>
                    <input class="easyui-textbox" name="showName" style="width: 300px;"
                           data-options="editable:true,required:true"/>
                </td>
            </tr>
            <tr>
                <td>属性名称:</td>
                <td><input class="easyui-textbox" name="propertyName" style="width: 300px;"
                           data-options="editable:true,required:true"/></td>
            </tr>
            <tr>
                <td>描述:</td>
                <td>
                    <input class="easyui-textbox" name="showDesc" style="width: 300px;"
                           data-options="editable:true,required:true"/>
                </td>
            </tr>
            <tr>
                <td>最小值:</td>
                <td><input class="easyui-numberbox" name="minValue" value="0" style="width: 300px;"
                           data-options="editable:true,required:true"/></td>
            </tr>
            <tr>
                <td>最大值:</td>
                <td><input class="easyui-numberbox" name="maxValue" value="0" style="width: 300px;"
                           data-options="editable:true,required:true"/></td>
            </tr>
            <tr>
                <td>指标级别:</td>
                <td><input class="easyui-numberbox" name="targetLevel" value="0" style="width: 100px;"
                           data-options="editable:true,required:true"/></td>
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

    $('#target-list').datagrid({
        url: '/targetCategory/listPage',
        title: '指标列表',
        pagePosition: 'top',
        singleSelect: true,
        rownumbers: true,
        collapsible: true,
        pagination: true,
        pageSize: 20,
        pageList: [20, 50, 100],
        toolbar: [{
            text: '新增',
            iconCls: 'icon-add',
            handler: function () {
                addTarget();
            }
        }, '-', {
            text: '编辑',
            iconCls: 'icon-edit',
            handler: function () {
                editTarget();
            }
        }, '-', {
                text: '删除',
                iconCls: 'icon-cancel',
                handler: function () {
                    deleteTarget();
                }
            }
        ],
        columns: [[
            {field: 'id', checkbox: true},
            {field: 'showName', title: '名称', width: 150, align: 'center'},
            {field: 'propertyName', title: '属性名称', width: 80, align: 'center'},
            {field: 'showDesc', title: '描述', width: 300, align: 'left'},
            {field: 'minValue', title: '最小值', width: 60, align: 'center'},
            {field: 'maxValue', title: '最大值', width: 60, align: 'center'},
            {field: 'targetLevel', title: '指标级别', width: 80, align: 'center'},
            {field: 'remark', title: '备注', width: 200, align: 'left'},
            {field: 'createTime', title: '创建时间', width: 150, align: 'center'},
            {field: 'updateTime', title: '修改时间', width: 150, align: 'center'},
        ]],
        onBeforeLoad: function (param) {
            param.pageNo = param.page;
            param.pageSize = param.rows;
            param.targetLevel = $('#listg_searchLevel').val();
            return true;
        }
    });

    addTarget = function () {
        $("#editTarget").dialog({
            title: '新增指标',
            width: 500,
            height: 500,
            top: 60,
            left: 150,
            closed: false,
            cache: false,
            modal: true,
            buttons: [{
                text: '保存',
                handler: function () {
                    if (!$("#editTargetForm").form('validate')) {
                        return false;
                    }
                    var params = $("#editTargetForm").serialize();
                    $.post("/targetCategory/add", params, function (data) {
                        if (data.code == 200) {
                            $.messager.alert('提示', '新增成功!', 'info',
                                function () {
                                    $("#editTarget").dialog('close');
                                    $("#target-list").datagrid("reload");
                                });
                        } else {
                            $.messager.alert('提示', data.msg, 'warning');
                        }
                    });
                }
            }, {
                text: '关闭',
                handler: function () {
                    $("#editTarget").dialog("close");
                }
            }],
            onBeforeClose: function () {
                $("#editTargetForm").form("clear");
            }
        });
    }

    editTarget = function () {
        var ids = getTargetListSelectionsIds();
        if (ids.length == 0) {
            $.messager.alert('提示', '必须选择一条数据才能编辑!');
            return;
        }
        if (ids.indexOf(',') > 0) {
            $.messager.alert('提示', '只能选择一条数据!');
            return;
        }
        $("#editTarget").dialog({
            title: '编辑指标',
            width: 500,
            height: 500,
            top: 60,
            left: 150,
            closed: false,
            cache: false,
            modal: true,
            buttons: [{
                text: '保存',
                handler: function () {
                    if (!$("#editTargetForm").form('validate')) {
                        return false;
                    }
                    var params = $("#editTargetForm").serialize();
                    $.post("/targetCategory/update", params, function (data) {
                        if (data.code == 200) {
                            $.messager.alert('提示', '修改成功!', 'info',
                                function () {
                                    $("#editTarget").dialog('close');
                                    $("#target-list").datagrid("reload");
                                });
                        } else {
                            $.messager.alert('提示', data.msg, 'warning');
                        }
                    });
                }
            }, {
                text: '关闭',
                handler: function () {
                    $("#editTarget").dialog("close");
                }
            }],
            onBeforeClose: function () {
                $("#editTargetForm").form("clear");
            }
        });
        var data = $("#target-list").datagrid("getSelected");
        $("#editTargetForm").form("load", data);
    }

    deleteTarget = function () {
        var ids = getTargetListSelectionsIds();
        if (ids.length == 0) {
            $.messager.alert('提示', '未选中数据!');
            return;
        }
        $.messager.confirm('确认', '确定删除ID为 ' + ids + ' 的公司详情吗？', function (r) {
            if (r) {
                var params = {"id": ids};
                $.post("/targetCategory/delete", params, function (data) {
                    if (data.code == 200) {
                        $.messager.alert('提示', '删除成功!', 'info',
                            function () {
                                $("#target-list").datagrid("reload");
                            });
                    }
                });
            }
        });
    }

    targetListSearch = function () {
        $('#target-list').datagrid('load');
    }

    getTargetListSelectionsIds = function () {
        var itemList = $("#target-list");
        var sels = itemList.datagrid("getSelections");
        var ids = [];
        for (var i in sels) {
            ids.push(sels[i].id);
        }
        ids = ids.join(",");
        return ids;
    }

</script>
