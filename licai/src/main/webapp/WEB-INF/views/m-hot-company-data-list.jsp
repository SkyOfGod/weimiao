<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div style="width: 100%;height: 40px">
    复盘日期:&nbsp;&nbsp;<input class="easyui-textbox" id="listm_searchDataDate">
    连扳数:&nbsp;&nbsp;<input class="easyui-textbox" id="listm_searchContinuityTime">
    <input type="hidden" id="listm_searchContinuityTimeKey"/>
    热点:&nbsp;&nbsp;<input class="easyui-textbox" id="listm_searchHotTypeId">
    公司:&nbsp;&nbsp;<input class="easyui-textbox" id="listm_searchHotCompanyId">
    <button class="easyui-linkbutton" iconCls="icon-search" onclick="hotCompanyDataListSearch()">搜索</button>
</div>
<table id="hot-company-data-list" style="width:100%;height:1120px"></table>

<div id="editHotCompanyData" class="easyui-dialog" data-options="closed:true">
    <form id="editHotCompanyDataForm" method="post">
        <table cellpadding="5">
            <input type="hidden" name="id">
            <tr>
                <td>股票代码:</td>
                <td>
                    <input class="easyui-textbox" name="code" style="width: 300px;" data-options="editable:true,required:true"/>
                </td>
            </tr>
            <tr>
                <td>公司名称:</td>
                <td><input id="editHotCompanyDataName" class="easyui-textbox" name="name" style="width: 300px;" data-options="editable:true,required:false"/></td>
            </tr>
            <tr>
                <td>板块:</td>
                <td>
                    <input id="editHotCompanyDataHotTypeId" class="easyui-textbox" name="hotTypeId" style="width: 180px;" data-options="editable:true,required:true"/>
                </td>
            </tr>
            <tr>
                <td>流通市值(亿):</td>
                <td>
                    <input id="editHotCompanyDataCirculationMarketValue" class="easyui-textbox" name="circulationMarketValue" value="0" style="width: 300px;"
                           data-options="editable:true,required:true"/>
                </td>
            </tr>
            <tr>
                <td>涨停时间:</td>
                <td>
                    <input class="easyui-datetimebox" name="fullTime" value="0" style="width: 300px;"
                           data-options="editable:false,required:true"/>
                </td>
            </tr>
            <tr>
                <td>当前连扳次数:</td>
                <td>
                    <select id="editHotCompanyDataContinuityTime" class="easyui-combobox" name="continuityTime" style="width: 100px;" data-options="editable:false,required:true">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                        <option value="12">13</option>
                        <option value="12">14</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>排序:</td>
                <td>
                    <select class="easyui-combobox" name="sort" style="width: 100px;" data-options="editable:false,required:true">
                        <option value="10">10</option>
                        <option value="9">9</option>
                        <option value="8">8</option>
                        <option value="7">7</option>
                        <option value="6">6</option>
                        <option value="5">5</option>
                        <option value="4">4</option>
                        <option value="3">3</option>
                        <option value="2">2</option>
                        <option value="1">1</option>
                        <option value="0">0</option>
                        <option value="-1">-1</option>
                        <option value="-2">-2</option>
                        <option value="-3">-3</option>
                        <option value="-4">-4</option>
                        <option value="-5">-5</option>
                        <option value="-6">-6</option>
                        <option value="-7">-7</option>
                        <option value="-8">-8</option>
                        <option value="-9">-9</option>
                        <option value="-10">-10</option>
                        <option value="-11">-11</option>
                        <option value="-12">-12</option>
                        <option value="-13">-13</option>
                        <option value="-14">-14</option>
                        <option value="-15">-15</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>最大换手%:</td>
                <td>
                    <input id="editHotCompanyDataMaxChange" class="easyui-textbox" name="maxChange" value="0" style="width: 300px;"
                           data-options="editable:true,required:false"/>
                </td>
            </tr>
            <tr>
                <td>封单(亿):</td>
                <td>
                    <input class="easyui-textbox" name="noDeal" value="0" style="width: 300px;"
                           data-options="editable:true,required:false"/>
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

    $("#listm_searchContinuityTime").combobox({
        valueField: 'key',
        textField: 'value',
        data: [{
            key: '',
            value: '全部'
        }, {
            key: '-20',
            value: '秒板'
        }, {
            key: '-1',
            value: '大于1次'
        }, {
            key: '-2',
            value: '大于2次'
        }, {
            key: '1',
            value: '1次'
        }, {
            key: '2',
            value: '2次'
        }, {
            key: '3',
            value: '3次'
        }, {
            key: '4',
            value: '4次'
        }, {
            key: '5',
            value: '5次'
        }, {
            key: '6',
            value: '6次'
        }, {
            key: '7',
            value: '7次'
        }, {
            key: '8',
            value: '8次'
        }, {
            key: '9',
            value: '9次'
        }, {
            key: '10',
            value: '10次'
        }],
        onSelect: function (record) {
            $("#listm_searchContinuityTimeKey").val(record.key);
        }
    });

    $("#listm_searchDataDate").combogrid({
        panelWidth: 180,
        idField: 'key',
        textField: 'key',
        url: '/hotCompanyData/dataDateCombogrid',
        mode: 'remote',
        delay: 500,
        columns: [[
            {field: 'key', title: '日期', width: 110, align: 'center'},
            {field: 'value', title: '星期', width: 50, align: 'center'}
        ]],
    });

    $("#listm_searchHotCompanyId").combogrid({
        panelWidth: 250,
        idField: 'id',
        textField: 'name',
        url: '/hotCompany/combogrid',
        mode: 'remote',
        delay: 500,
        columns: [[
            {field: 'id', title: '主键', width: 40, align: 'center'},
            {field: 'code', title: '编码', width: 80, align: 'center'},
            {field: 'name', title: '名称', width: 80, align: 'center'}
        ]],
    });

    $("#listm_searchHotTypeId").combogrid({
        panelWidth: 200,
        idField: 'id',
        textField: 'name',
        url: '/hotType/combogrid',
        mode: 'remote',
        delay: 500,
        columns: [[
            {field: 'id', title: '主键', width: 40, align: 'center'},
            {field: 'name', title: '名称', width: 100, align: 'center'},
            {field: 'sort', title: '排序', width: 40, align: 'center'}
        ]],
    });

    $("#editHotCompanyData [name='code']").combogrid({
        panelWidth: 300,
        idField: 'code',
        textField: 'name',
        url: '/hotCompany/combogrid',
        mode: 'remote',
        delay: 500,
        columns: [[
            {field: 'id', title: '主键', width: 40, align: 'center'},
            {field: 'code', title: '编码', width: 80, align: 'center'},
            {field: 'name', title: '名称', width: 80, align: 'center'}
        ]],
        onSelect: function (index, value) {
            $("#editHotCompanyDataName").textbox('setValue', value.name);
            $("#editHotCompanyDataHotTypeId").textbox('setValue', value.hotTypeId);
            // $("#editHotCompanyDataCirculationMarketValue").textbox('setValue', value.circulationMarketValue);
            // $("#editHotCompanyDataContinuityTime").textbox('setValue', value.continuityTime + 1);
            $("#editHotCompanyDataMaxChange").textbox('setValue', value.maxChange);
        }
    });

    $("#editHotCompanyData [name='hotTypeId']").combogrid({
        panelWidth: 200,
        idField: 'id',
        textField: 'name',
        url: '/hotType/combogrid',
        mode: 'remote',
        delay: 500,
        columns: [[
            {field: 'id', title: '主键', width: 40, align: 'center'},
            {field: 'name', title: '名称', width: 100, align: 'center'},
            {field: 'sort', title: '排序', width: 40, align: 'center'},
        ]],
    });

    $('#hot-company-data-list').datagrid({
        url: '/hotCompanyData/listPage',
        title: '复盘列表',
        pagePosition: 'top',
        singleSelect: true,
        rownumbers: true,
        collapsible: true,
        pagination: true,
        pageSize: 100,
        pageList: [20, 50, 100],
        toolbar: [{
            text: '新增',
            iconCls: 'icon-add',
            handler: function () {
                addHotCompanyData();
            }
        }, '-', {
            text: '编辑',
            iconCls: 'icon-edit',
            handler: function () {
                editHotCompanyData();
            }
        }, '-', {
            text: '删除',
            iconCls: 'icon-cancel',
            handler: function () {
                deleteHotCompanyData();
            }
        }
        ],
        columns: [[
            {field: 'id', checkbox: true},
            {field: 'code', title: '股票代码', width: 64, align: 'center'},
            {field: 'name', title: '公司名称', width: 64, align: 'center'},
            {field: 'hotType', title: '热点', width: 90, align: 'center'},
            {field: 'continuityTime', title: '当前连扳数', width: 60, align: 'center',
                formatter: function (value, row, index) {
                    if (value > 1) {
                        return '<span style="color:red;">' + value + '</span>';
                    }
                    return value;
                }
            },
            {field: 'safeChangeMarketValue', title: '安全换值(亿)', width: 80, align: 'center',
                formatter: function (value, row, index) {
                    if (value > 0) {
                        return value + '亿'
                    }
                    return value;
                }
            },
            {field: 'circulationMarketValue', title: '流通市值(亿)', width: 80, align: 'center'},
            {field: 'safeChange', title: '安全换手%', width: 80, align: 'center',
                formatter: function (value, row, index) {
                    if (value > 0) {
                        return value + '%'
                    }
                    return value;
                }
            },
            {field: 'maxChange', title: '参考换手%', width: 80, align: 'center'},
            {field: 'percent', title: '流通股占比(%)', width: 60, align: 'center',
                formatter: function (value, row, index) {
                    if (value > 0) {
                        return value + '%'
                    }
                    return value;
                }
            },
            // {field: 'noDealPercent', title: '封单率%', width: 60, align: 'center'},
            // {field: 'noDeal', title: '封单(亿)', width: 60, align: 'center'},
            {field: 'fullTime', title: '涨停时间', width: 70, align: 'center'},
            {field: 'dataDate', title: '复盘日期', width: 90, align: 'center'},
            {field: 'hotType1', title: '概念1', width: 80, align: 'center'},
            {field: 'hotType2', title: '概念2', width: 80, align: 'center'},
            {field: 'hotType3', title: '概念3', width: 80, align: 'center'},
            {field: 'hotType4', title: '概念4', width: 80, align: 'center'},
            {field: 'hotType5', title: '概念5', width: 80, align: 'center'},
            {field: 'remark', title: '备注', width: 300, align: 'left',
                formatter: function (value, row, index) {
                    if (value == null) {
                        return value;
                    }
                    return '<span title="' + value + '">' + value + '</span>';
                }
            },
            {field: 'firstTime', title: '首扳数', width: 48, align: 'center'},
            {field: 'secondTime', title: '二扳数', width: 48, align: 'center'},
            {field: 'thirdTime', title: '三扳数', width: 48, align: 'center'},
            {field: 'forthTime', title: '四扳数', width: 48, align: 'center'},
            {field: 'fifthTime', title: '五扳数', width: 48, align: 'center'},
            {field: 'sixthTime', title: '六扳数', width: 48, align: 'center'},
            {field: 'seventhTime', title: '七扳数', width: 48, align: 'center'},
            {field: 'sort', title: '排序', width: 40, align: 'center'},
            {field: 'createTime', title: '创建时间', width: 150, align: 'center'},
            {field: 'updateTime', title: '修改时间', width: 150, align: 'center'},
        ]],
        onBeforeLoad: function (param) {
            param.pageNo = param.page;
            param.pageSize = param.rows;
            param.dataDate = $('#listm_searchDataDate').val();
            param.hotTypeId = $('#listm_searchHotTypeId').val();
            param.hotCompanyId = $('#listm_searchHotCompanyId').val();
            param.continuityTime = $('#listm_searchContinuityTimeKey').val();
            return true;
        }
    });

    addHotCompanyData = function () {
        $("#editHotCompanyData").dialog({
            title: '新增复盘数据',
            width: 500,
            height: 600,
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
                    if (!$("#editHotCompanyDataForm").form('validate')) {
                        return false;
                    }
                    var params = $("#editHotCompanyDataForm").serialize();
                    $.post("/hotCompanyData/add", params, function (data) {
                        if (data.code == 200) {
                            $.messager.alert('提示', '新增成功!', 'info',
                                function () {
                                    $("#editHotCompanyData").dialog('close');
                                    $("#hot-company-data-list").datagrid("reload");
                                });
                        } else {
                            $.messager.alert('提示', data.msg, 'warning');
                        }
                    });
                }
            }/*, {
                text: '关闭',
                handler: function () {
                    $("#editHotCompanyData").dialog("close");
                }
            }*/],
            onBeforeClose: function () {
                $("#editHotCompanyDataForm").form("clear");
            }
        });
    }

    editHotCompanyData = function () {
        var ids = getHotCompanyDataListSelectionsIds();
        if (ids.length == 0) {
            $.messager.alert('提示', '必须选择一条数据才能编辑!');
            return;
        }
        if (ids.indexOf(',') > 0) {
            $.messager.alert('提示', '只能选择一条数据!');
            return;
        }
        $("#editHotCompanyData").dialog({
            title: '编辑复盘数据',
            width: 500,
            height: 600,
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
                    if (!$("#editHotCompanyDataForm").form('validate')) {
                        return false;
                    }
                    var params = $("#editHotCompanyDataForm").serialize();
                    $.post("/hotCompanyData/update", params, function (data) {
                        if (data.code == 200) {
                            $.messager.alert('提示', '修改成功!', 'info',
                                function () {
                                    $("#editHotCompanyData").dialog('close');
                                    $("#hot-company-data-list").datagrid("reload");
                                });
                        } else {
                            $.messager.alert('提示', data.msg, 'warning');
                        }
                    });
                }
            }/*, {
                text: '关闭',
                handler: function () {
                    $("#editHotCompanyData").dialog("close");
                }
            }*/],
            onBeforeClose: function () {
                $("#editHotCompanyDataForm").form("clear");
            }
        });
        var data = $("#hot-company-data-list").datagrid("getSelected");
        $("#editHotCompanyDataForm").form("load", data);
    }

    deleteHotCompanyData = function () {
        var ids = getHotCompanyDataListSelectionsIds();
        if (ids.length == 0) {
            $.messager.alert('提示', '未选中数据!');
            return;
        }
        $.messager.confirm('确认', '确定删除ID为 ' + ids + ' 的复盘数据吗？', function (r) {
            if (r) {
                var params = {"id": ids};
                $.post("/hotCompanyData/delete", params, function (data) {
                    if (data.code == 200) {
                        $.messager.alert('提示', '删除成功!', 'info',
                            function () {
                                $("#hot-company-data-list").datagrid("reload");
                            });
                    }
                });
            }
        });
    }

    hotCompanyDataListSearch = function () {
        $('#hot-company-data-list').datagrid('load');
    }

    getHotCompanyDataListSelectionsIds = function () {
        var itemList = $("#hot-company-data-list");
        var sels = itemList.datagrid("getSelections");
        var ids = [];
        for (var i in sels) {
            ids.push(sels[i].id);
        }
        ids = ids.join(",");
        return ids;
    }

</script>
