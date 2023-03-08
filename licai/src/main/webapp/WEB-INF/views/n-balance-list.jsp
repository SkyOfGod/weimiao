<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div style="width: 100%;height: 40px;">
    公司:&nbsp;&nbsp;<input class="easyui-textbox" id="listn_searchCompanyId">
    年份:&nbsp;&nbsp;<input class="easyui-textbox" id="listn_searchYear">
    <button style="margin-left: 10px" class="easyui-linkbutton" iconCls="icon-search" onclick="nBalanceListSearch()">
        搜索
    </button>
</div>
<table id="n-balance-list" style="width:100%;height:600px"></table>

<div id="nbalanceEdit" class="easyui-dialog" data-options="closed:true">
    <form id="nbalanceEditForm" method="post">
        <table cellpadding="8">
            <input type="hidden" name="id">
            <tr>
                <td>公司:</td>
                <td>
                    <input class="easyui-textbox" name="name" style="width: 150px;" data-options="required:true"/>
                </td>
                <td>年份:</td>
                <td>
                    <input class="easyui-textbox" name="year" style="width: 150px;" data-options="editable:false"/>
                </td>
            </tr>
            <tr>
                <td>资产合计:</td>
                <td>
                    <input class="easyui-numberbox" name="totalAssets" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>负债合计:</td>
                <td>
                    <input class="easyui-numberbox" name="totalLiabilities" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>货币资金:</td>
                <td>
                    <input class="easyui-numberbox" name="monetaryFunds" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>交易性金融资产:</td>
                <td>
                    <input class="easyui-numberbox" name="tradingFinancialAssets" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>其他流动资产:</td>
                <td>
                    <input class="easyui-numberbox" name="otherCurrentAssets" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>长期借款:</td>
                <td>
                    <input class="easyui-numberbox" name="longTermBorrowing" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>短期借款:</td>
                <td>
                    <input class="easyui-numberbox" name="shortTermBorrowing" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>一年到期的非流动负债:</td>
                <td>
                    <input class="easyui-numberbox" name="nonCurrentLiability" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>长期应付款合计:</td>
                <td>
                    <input class="easyui-numberbox" name="totalLongTermPayables" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>应付票据:</td>
                <td>
                    <input class="easyui-numberbox" name="notesPayable" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>应付账款:</td>
                <td>
                    <input class="easyui-numberbox" name="accountsPayable" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>应付票据及应付账款:</td>
                <td>
                    <input class="easyui-numberbox" name="notesAccounts" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>预收款项:</td>
                <td>
                    <input class="easyui-numberbox" name="accountCollectedAdvance" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>合同负债:</td>
                <td>
                    <input class="easyui-numberbox" name="liabilityForContract" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>应收票据:</td>
                <td>
                    <input class="easyui-numberbox" name="notesReceivable" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>应收账款:</td>
                <td>
                    <input class="easyui-numberbox" name="accountsReceivable" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>预付款项:</td>
                <td>
                    <input class="easyui-numberbox" name="prepayment" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>固定资产合计:</td>
                <td>
                    <input class="easyui-numberbox" name="totalFixedAssets" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>固定资产:</td>
                <td>
                    <input class="easyui-numberbox" name="fixedAssets" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>在建工程合计:</td>
                <td>
                    <input class="easyui-numberbox" name="totalWorksInProgress" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>在建工程:</td>
                <td>
                    <input class="easyui-numberbox" name="workInProgress" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>工程物资:</td>
                <td>
                    <input class="easyui-numberbox" name="materialsForConstruction" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>可供出售的金融资产:</td>
                <td>
                    <input class="easyui-numberbox" name="financialAssetsSale" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>持有到期的金融资产:</td>
                <td>
                    <input class="easyui-numberbox" name="holdFinancialAssets" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>长期股权投资:</td>
                <td>
                    <input class="easyui-numberbox" name="longTermEquityInvestment" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>投资性房地产:</td>
                <td>
                    <input class="easyui-numberbox" name="investmentRealEstate" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>其他权益工具投资:</td>
                <td>
                    <input class="easyui-numberbox" name="investmentInOther" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>其他非流动的金融资产:</td>
                <td>
                    <input class="easyui-numberbox" name="otherNonCurrentFinancialAssets" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>存货:</td>
                <td>
                    <input class="easyui-numberbox" name="inventory" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>商誉:</td>
                <td>
                    <input class="easyui-numberbox" name="goodwill" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>归属于母公司的所有者权益合计:</td>
                <td>
                    <input class="easyui-numberbox" name="totalOwnersEquity" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>备注:</td>
                <td><input class="easyui-textbox" name="remark" data-options="multiline:true,validType:'length[0,150]'"
                           style="height:60px;width: 200px;"/></td>
            </tr>
            <tr>
                <td>应付债券:</td>
                <td>
                    <input class="easyui-numberbox" name="bondsPayable" style="width: 150px;"
                           data-options="required:true"/>
                </td>

            </tr>
        </table>
    </form>
</div>

<div id="importExcel-n" class="easyui-dialog" title="导入文件" style="width:450px; padding:20px;" data-options="closed:true">
    <form id="uploadExcel-n"  method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td>公司:</td>
                <td><input id="upload_searchCompanyId_n" name="id" class="easyui-textbox" style="width: 250px;"/></td>
            </tr>
            <tr>
                <td>文件:</td>
                <td><input id="file-n" name="file" class="easyui-filebox" style="width: 250px;"
                           data-options="prompt:'请选择文件...'"/></td>
            </tr>
        </table>
    </form>
</div>

<script type="text/javascript" charset="utf-8">

    $("#nbalanceEditForm [name='year']").combogrid({
        panelWidth: 150,
        idField: 'key',
        textField: 'value',
        url: '/company/yearCombogrid',
        mode: 'remote',
        delay: 500,
        columns: [[
            {field: 'value', title: '年份', width: 130, align: 'center'},
        ]],
    });

    $("#listn_searchYear").combogrid({
        panelWidth: 170,
        idField: 'key',
        textField: 'value',
        url: '/company/yearCombogrid',
        mode: 'remote',
        delay: 500,
        columns: [[
            {field: 'value', title: '年份', width: 150, align: 'center'},
        ]],
    });


    $("#listn_searchCompanyId").combogrid({
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

    $("#upload_searchCompanyId_n").combogrid({
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

    $('#n-balance-list').datagrid({
        url: '/balanceSheet/listpage',
        title: '合并资产负债表',
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
                    editBalanceData();
                }
            }, '-',{
                text: '删除',
                iconCls: 'icon-cancel',
                handler: function () {
                    deleteBalanceData();
                }
            },'-',{
                text: '导入',
                iconCls: 'icon-save',
                handler: function () {
                    importBalanceData();
                }
            }
        ],
        columns: [[
            {field: 'id', checkbox: true},
            {field: 'code', title: '股票代码', width: 100, align: 'center'},
            {field: 'name', title: '公司名称', width: 100, align: 'center'},
            {field: 'year', title: '年份', width: 40, align: 'center'},
            {field: 'totalAssets', title: '总资产', width: 120, align: 'right'},
            {field: 'totalLiabilities', title: '总负债', width: 100, align: 'right'},
            {field: 'monetaryFunds', title: '货币资金', width: 100, align: 'right'},
            {field: 'tradingFinancialAssets', title: '交易性金融资产', width: 110, align: 'right'},
            {field: 'otherCurrentAssets', title: '其他流动资产', width: 100, align: 'right'},
            {field: 'shortTermBorrowing', title: '短期借款', width: 100, align: 'right'},
            {field: 'nonCurrentLiability', title: '一年到期的非流动负债', width: 120, align: 'right'},
            {field: 'longTermBorrowing', title: '长期借款', width: 100, align: 'right'},
            {field: 'totalLongTermPayables', title: '长期应付款合计', width: 100, align: 'right'},
            {field: 'bondsPayable', title: '应付债券', width: 100, align: 'right'},
            {field: 'notesPayable', title: '应付票据', width: 100, align: 'right'},
            {field: 'accountsPayable', title: '应付账款', width: 100, align: 'right'},
            {field: 'notesAccounts', title: '应付票据及应付账款', width: 100, align: 'right'},
            {field: 'accountCollectedAdvance', title: '预收款项', width: 100, align: 'right'},
            {field: 'liabilityForContract', title: '合同负债', width: 100, align: 'right'},
            {field: 'notesReceivable', title: '应收票据', width: 120, align: 'right'},
            {field: 'accountsReceivable', title: '应收账款', width: 100, align: 'right'},
            {field: 'prepayment', title: '预付款项', width: 120, align: 'right'},
            {field: 'totalFixedAssets', title: '固定资产合计', width: 120, align: 'right'},
            {field: 'fixedAssets', title: '固定资产', width: 100, align: 'right'},
            {field: 'totalWorksInProgress', title: '在建工程合计', width: 100, align: 'right'},
            {field: 'workInProgress', title: '在建工程', width: 100, align: 'right'},
            {field: 'materialsForConstruction', title: '工程物资', width: 100, align: 'right'},
            {field: 'financialAssetsSale', title: '可供出售的金融资产', width: 100, align: 'left'},
            {field: 'holdFinancialAssets', title: '持有到期的金融资产', width: 100, align: 'left'},
            {field: 'longTermEquityInvestment', title: '长期股权投资', width: 100, align: 'left'},
            {field: 'investmentRealEstate', title: '投资性房地产', width: 100, align: 'left'},
            {field: 'investmentInOther', title: '其他权益工具投资', width: 100, align: 'left'},
            {field: 'otherNonCurrentFinancialAssets', title: '其他非流动的金融资产', width: 100, align: 'left'},
            {field: 'inventory', title: '存货', width: 100, align: 'left'},
            {field: 'goodwill', title: '商誉', width: 100, align: 'left'},
            {field: 'totalOwnersEquity', title: '归属于母公司的所有者权益合计', width: 100, align: 'left'},
            {field: 'remark', title: '备注', width: 200, align: 'left'},
            {field: 'createTime', title: '创建时间', width: 150, align: 'center'},
            {field: 'updateTime', title: '修改时间', width: 150, align: 'center'},
        ]],
        onBeforeLoad: function (param) {
            param.pageNo = param.page;
            param.pageSize = param.rows;
            param.companyId = $("#listn_searchCompanyId").val();
            param.year = $("#listn_searchYear").val();
            return true;
        }
    });

    nBalanceListSearch = function () {
        $('#n-balance-list').datagrid('load');
    }


    importBalanceData = function(){
        $("#importExcel-n").dialog({
            title: 'Excel上传',
            width: 400,
            height: 200,
            closed: false,
            cache: false,
            modal: true,
            buttons: [{
                text: '上传',
                handler: function () {
                    var compName= $('#upload_searchCompanyId_n').filebox('getValue');
                    if (compName === "") {
                        $.messager.alert('提示', '请选择公司!');
                        return;
                    }
                    //进行基本校验
                    var fileName = $('#file-n').filebox('getValue');
                    if(fileName == ""){
                        $.messager.alert('提示','请选择上传文件！','info');
                        return;
                    }
                    //对文件格式进行校验
                    var d1 = /\.[^\.]+$/.exec(fileName);
                    if(d1 == ".xlsx" || d1 == ".xls"){
                        var formdata = new FormData($("#uploadExcel-n")[0]);
                        $.ajax({
                            url: "/balanceSheet/upload",
                            type: "POST",
                            data:formdata,
                            contentType : "application/json;charset=UTF-8",
                            dataType: "json",
                            processData: false,  // 告诉jQuery不要去处理发送的数据
                            contentType: false,   // 告诉jQuery不要去设置Content-Type请求头
                            success: function (data) {
                                if (data.code == 200) {
                                    $.messager.alert('提示', '导入成功!', 'info',
                                        function () {
                                            $("#importExcel-n").dialog('close');
                                            $("#n-balance-list").datagrid("reload");
                                        });
                                } else {
                                    $.messager.alert('提示', data.msg, 'warning');
                                }
                            }
                        })
                    }else{
                        $.messager.alert('提示','请选择xlsx或xls格式文件！','info');
                        $('#fileUpload').filebox('setValue','');
                    }

                }
            },{
                text: '关闭',
                handler: function () {
                    $("#importExcel-n").dialog("close");
                }
            }],
            onBeforeClose: function () {
                $("#nbalanceEditForm").form("clear");
            }
        });
    }

    editBalanceData = function () {
        var ids = getBalanceListSelectionsIds();
        if (ids.length === 0) {
            $.messager.alert('提示', '必须选择一条数据才能编辑!');
            return;
        }
        if (ids.indexOf(',') > 0) {
            $.messager.alert('提示', '只能选择一条数据!');
            return;
        }
        $("#nbalanceEdit").dialog({
            title: '编辑资产负债表数据',
            width: 800,
            height: 800,
            top: 10,
            left: 150,
            closed: false,
            cache: false,
            modal: true,
            buttons: [{
                text: '保存',
                handler: function () {
                    var params = $("#nbalanceEditForm").serialize();
                    $.post("/balanceSheet/update", params, function (data) {
                        if (data.code == 200) {
                            $.messager.alert('提示', '修改成功!', 'info',
                                function () {
                                    $("#nbalanceEdit").dialog('close');
                                    $("#n-balance-list").datagrid("reload");
                                });
                        } else {
                            $.messager.alert('提示', data.msg, 'warning');
                        }
                    });
                }
            }, {
                text: '关闭',
                handler: function () {
                    $("#nbalanceEdit").dialog("close");
                }
            }],
            onBeforeClose: function () {
                $("#nbalanceEditForm").form("clear");
            }
        });
        var data = $("#n-balance-list").datagrid("getSelected");
        $("#nbalanceEditForm").form("load", data);
    }

    deleteBalanceData = function () {
        var ids = getBalanceListSelectionsIds();
        if (ids.length === 0) {
            $.messager.alert('提示', '未选中数据!');
            return;
        }
        $.messager.confirm('确认', '确定删除ID为 ' + ids + ' 的资产负债数据吗？', function (r) {
            if (r) {
                var params = {"id": ids};
                $.post("/balanceSheet/delete", params, function (data) {
                    if (data.code == 200) {
                        $.messager.alert('提示', '删除成功!', 'info',
                            function () {
                                $("#n-balance-list").datagrid("reload");
                            });
                    }
                });
            }
        });
    }

    getBalanceListSelectionsIds = function () {
        var itemList = $("#n-balance-list");
        var sels = itemList.datagrid("getSelections");
        var ids = [];
        for (var i in sels) {
            ids.push(sels[i].id);
        }
        ids = ids.join(",");
        return ids;
    }

</script>
