<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div style="width: 100%;height: 40px;">
    公司:&nbsp;&nbsp;<input class="easyui-textbox" id="listq_searchCompanyId">
    年份:&nbsp;&nbsp;<input class="easyui-textbox" id="listq_searchYear">
    <button style="margin-left: 10px" class="easyui-linkbutton" iconCls="icon-search"
            onclick="qCompanytargetlistSearch()">
        搜索
    </button>
</div>
<table id="q-companyTarget-list" style="width:100%;height:600px"></table>


<script type="text/javascript" charset="utf-8">

    $("#qCompanytargetEditForm [name='year']").combogrid({
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

    $("#listq_searchYear").combogrid({
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


    $("#listq_searchCompanyId").combogrid({
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

    $("#upload_searchCompanyId_p").combogrid({
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

    $('#q-companyTarget-list').datagrid({
        url: '/companyTarget/listPage',
        title: '公司指标',
        pagePosition: 'top',
        rownumbers: true,
        singleSelect: true,
        collapsible: true,
        pagination: true,
        pageSize: 20,
        pageList: [20, 50, 100],
        toolbar: [
            {
                text: '重算',
                iconCls: 'icon-edit',
                handler: function () {
                    editCompanyTargetData();
                }
            }, '-', {
                text: '删除',
                iconCls: 'icon-cancel',
                handler: function () {
                    deleteCompanyTargetData();
                }
            }
        ],
        columns: [[
            {field: 'id', checkbox: true},
            {field: 'code', title: '股票代码', width: 100, align: 'center'},
            {field: 'name', title: '公司名称', width: 100, align: 'center'},
            {field: 'year', title: '年份', width: 40, align: 'center'},
            {field: 'roe', title: '净资产收益率ROE', width: 150, align: 'right',
                formatter: function (value, row, index) {
                     if (value != 0) {
                          if (value/100 >= 20) {
                              return '<span style="color:green;">' + value/100 + '%</span>';
                          }
                          if (value/100 <= 10) {
                              return '<span style="color:red;">' + value/100 + '%</span>';
                          }
                          return value/100 + '%';
                     }
                     return value;
                 }
            },
            {field: 'growthRateTotalAssets', title: '总资产增长率', width: 150, align: 'right',
                formatter: function (value, row, index) {
                     if (value != 0) {
                         if (value/100 >= 10) {
                             return '<span style="color:green;">' + value/100 + '%</span>';
                         }
                         if (value/100 <= 0) {
                             return '<span style="color:red;">' + value/100 + '%</span>';
                         }
                         return value/100 + '%';
                    }
                    return value;
                }
            },
            {field: 'totalDebtGrowthRate', title: '资产负债率', width: 150, align: 'right',
                formatter: function (value, row, index) {
                    if (value != 0) {
                         if (value/100 >= 70) {
                             return '<span style="color:red;">' + value/100 + '%</span>';
                         }
                         if (value/100 < 70 && value/100 >= 40) {
                             return '<span style="color:yellow;">' + value/100 + '%</span>';
                         }
                        return value/100 + '%';
                    }
                    return value;
                }
            },
            {field: 'difference', title: '准货币资金减有息负债的差额', width: 150, align: 'right',
                formatter: function (value, row, index) {
                     if (value != 0) {
                          if (value <= 0) {
                               return '<span style="color:yellow;">' + value + '%</span>';
                          }
                          return value;
                     }
                     return value;
                }
            },
            {field: 'differencePayableReceivable', title: '应付预收减应收预付差额', width: 150, align: 'right',
                formatter: function (value, row, index) {
                     if (value != 0) {
                          if (value <= 0) {
                              return '<span style="color:yellow;">' + value + '%</span>';
                          }
                          return '<span style="color:green;">' + value + '%</span>';
                     }
                     return value;
                }
            },
            {field: 'accountsReceivablePercentage', title: '应收账款占总资产的比率', width: 150, align: 'right',
                formatter: function (value, row, index) {
                    if (value != 0) {
                         if (value/100 <= 3) {
                             return '<span style="color:green;">' + value/100 + '%</span>';
                         }
                         if (value/100 < 20 && value/100 >= 10) {
                             return '<span style="color:yellow;">' + value/100 + '%</span>';
                         }
                         if (value/100 >= 20) {
                             return '<span style="color:red;">' + value/100 + '%</span>';
                         }
                        return value/100 + '%';
                    }
                    return value;
                }
            },
            {field: 'proportionFixedAssets', title: '固定资产比例', width: 150, align: 'right',
                formatter: function (value, row, index) {
                    if (value != 0) {
                         if (value/100 >= 40) {
                             return '<span style="color:red;">' + value/100 + '%</span>';
                         }
                         return value/100 + '%';
                    }
                    return value;
                }
            },
            {field: 'ratioInvestmentAssets', title: '投资类资产占总资产的比率', width: 150, align: 'right',
                formatter: function (value, row, index) {
                    if (value != 0) {
                         if (value/100 >= 10) {
                             return '<span style="color:red;">' + value/100 + '%</span>';
                         }
                         return value/100 + '%';
                    }
                    return value;
                }
            },
            {field: 'ratioInventory', title: '存货占总资产的比率', width: 150, align: 'right',
                formatter: function (value, row, index) {
                    if (value != 0) {
                        if (value/100 >= 15 && row.accountsReceivablePercentage/100 > 5) {
                            return '<span style="color:red;">' + value/100 + '%</span>';
                        }
                        return value/100 + '%';
                    }
                    return value;
                }
            },
            {field: 'ratioGoodwill', title: '商誉占总资产的比率', width: 150, align: 'right',
                formatter: function (value, row, index) {
                    if (value != 0) {
                        if (value/100 >= 10) {
                            return '<span style="color:red;">' + value/100 + '%</span>';
                        }
                        return value/100 + '%';
                    }
                    return value;
                }
            },
            {field: 'increaseRateBusinessRevenue', title: '营业收入增长率', width: 150, align: 'right',
                formatter: function (value, row, index) {
                    if (value != 0) {
                        if (value/100 >= 10) {
                            return '<span style="color:green;">' + value/100 + '%</span>';
                        }
                        if (value/100 <= 0) {
                              return '<span style="color:red;">' + value/100 + '%</span>';
                        }
                        return value/100 + '%';
                    }
                    return value;
                }
            },
            {field: 'grossProfitRate', title: '毛利率', width: 150, align: 'right',
                formatter: function (value, row, index) {
                    if (value != 0) {
                        if (value/100 >= 40) {
                            return '<span style="color:green;">' + value/100 + '%</span>';
                        }
                        return value/100 + '%';
                    }
                    return value;
                }
            },
            {field: 'grossMarginFluctuation', title: '毛利率波幅', width: 150, align: 'right',
                formatter: function (value, row, index) {
                    if (value != 0) {
                        if (value/100 <= 10) {
                            return '<span style="color:green;">' + value/100 + '%</span>';
                        }
                        return value/100 + '%';
                    }
                    return value;
                }
            },
            {field: 'ratioPeriodExpenseGross', title: '期间费用率与毛利率的比率', width: 150, align: 'right',
                formatter: function (value, row, index) {
                    if (value != 0) {
                        if (value/100 > 60) {
                            return '<span style="color:red;">' + value/100 + '%</span>';
                        }
                        if (value/100 < 60 && value/100 >= 40) {
                            return '<span style="color:yellow;">' + value/100 + '%</span>';
                        }
                        return value/100 + '%';
                    }
                    return value;
                }
            },
            {field: 'ratioExpensesSales', title: '销售费用率', width: 150, align: 'right',
                formatter: function (value, row, index) {
                    if (value != 0) {
                        if (value/100 > 15) {
                            return '<span style="color:red;">' + value/100 + '%</span>';
                        }
                        return value/100 + '%';
                    }
                    return value;
                }
            },
            {field: 'operatingProfitRate', title: '主营利润率', width: 150, align: 'right',
                formatter: function (value, row, index) {
                    if (value != 0) {
                        if (value/100 > 15) {
                            return '<span style="color:green;">' + value/100 + '%</span>';
                        }
                        return value/100 + '%';
                    }
                    return value;
                }
            },
            {field: 'ratioMainOperating', title: '主营利润占营业利润的比率', width: 150, align: 'right',
                formatter: function (value, row, index) {
                    if (value != 0) {
                        if (value/100 > 80) {
                            return '<span style="color:green;">' + value/100 + '%</span>';
                        }
                        return value/100 + '%';
                    }
                    return value;
                }
            },
            {field: 'netProfitCashRatio', title: '净利润现金比率', width: 150, align: 'right',
                formatter: function (value, row, index) {
                    if (value != 0) {
                        if (value/100 > 80) {
                            return '<span style="color:green;">' + value/100 + '%</span>';
                        }
                        return value/100 + '%';
                    }
                    return value;
                }
            },
            {field: 'growthRateNetProfitAttributable', title: '归母净利润增长率', width: 150, align: 'right',
                formatter: function (value, row, index) {
                    if (value != 0) {
                        if (value/100 > 10) {
                            return '<span style="color:green;">' + value/100 + '%</span>';
                        }
                        if (value/100 < 0) {
                            return '<span style="color:red;">' + value/100 + '%</span>';
                        }
                        return value/100 + '%';
                    }
                    return value;
                }
            },
            {field: 'operatingCashFlow', title: '经营活动产生的现金流量净额', width: 150, align: 'right',
                formatter: function (value, row, index) {
                     if (value != 0) {
                          if (value < 0) {
                              return '<span style="color:red;">' + value + '%</span>';
                          }
                          return '<span style="color:green;">' + value + '%</span>';
                     }
                     return value;
                }
            }
            {field: 'growthRateNetCashFlow', title: '经营活动产生的现金流量净额增长率', width: 150, align: 'right',
                formatter: function (value, row, index) {
                    if (value != 0) {
                        if (value/100 > 0) {
                            return '<span style="color:green;">' + value/100 + '%</span>';
                        }
                        return value/100 + '%';
                    }
                    return value;
                }
            },
            {field: 'ratioCashPaid', title: '购建固定资产、无形资产和其他长期资产支付的现金占经营活动产生的现金流量净额比率', width: 150, align: 'right',
                formatter: function (value, row, index) {
                    if (value != 0) {
                        if (value/100 < 3) {
                            return '<span style="color:gray;">' + value/100 + '%</span>';
                        }
                        if (value/100 < 100 && value/100 >= 60) {
                            return '<span style="color:yellow;">' + value/100 + '%</span>';
                        }
                        if (value/100 >= 100) {
                            return '<span style="color:red;">' + value/100 + '%</span>';
                        }
                        return value/100 + '%';
                    }
                    return value;
                }
            },
            {field: 'shareOutBonus', title: '年度股利支付率/分红', width: 150, align: 'right',
                formatter: function (value, row, index) {
                    if (value != 0) {
                        if (value/100 < 20) {
                            return '<span style="color:red;">' + value/100 + '%</span>';
                        }
                        if (value/100 >= 70) {
                            return '<span style="color:yellow;">' + value/100 + '%</span>';
                        }
                        return value/100 + '%';
                    }
                    return value;
                }
            },
            {field: 'compactType', title: '公司类型', width: 80, align: 'right',
                formatter: function (value, row, index) {
                    if (value != null) {
                        if (value === '正负负' || value === '正正负') {
                            return '<span style="color:green;">' + value + '%</span>';
                        }
                        return '<span style="color:red;">' + value + '%</span>';
                    }
                    return value;
                }
            },
            {field: 'remark', title: '备注', width: 200, align: 'left'},
            {field: 'createTime', title: '创建时间', width: 150, align: 'center'},
            {field: 'updateTime', title: '修改时间', width: 150, align: 'center'},
        ]],
        onBeforeLoad: function (param) {
            param.pageNo = param.page;
            param.pageSize = param.rows;
            param.companyId = $("#listq_searchCompanyId").val();
            param.year = $("#listq_searchYear").val();
            return true;
        }
    });

    qCompanytargetlistSearch = function () {
        $('#q-companyTarget-list').datagrid('load');
    }


    editCompanyTargetData = function () {
        var itemList = $("#q-companyTarget-list");
        var sels = itemList.datagrid("getSelections");
        if (sels.length === 0) {
            $.messager.alert('提示', '未选中数据!');
            return;
        }

        $.messager.confirm('确认', '确定重算 【' + sels[0].name + '】 公司的【' + sels[0].year + '】的数据吗？', function (r) {
            if (r) {
                var params = {"companyId": sels[0].companyId, "year": sels[0].year};
                $.post("/companyTarget/calculate", params, function (data) {
                    if (data.code == 200) {
                        $.messager.alert('提示', '重算成功!', 'info',
                            function () {
                                $("#q-companyTarget-list").datagrid("reload");
                            });
                    } else {
                        $.messager.alert('提示', data.msg, 'warning');
                    }
                });
            }
        });
    }
    deleteCompanyTargetData = function () {
        var ids = getCompanyTargetlistSelectionsIds();
        if (ids.length === 0) {
            $.messager.alert('提示', '未选中数据!');
            return;
        }
        $.messager.confirm('确认', '确定删除ID为 ' + ids + ' 公司指标的数据吗？', function (r) {
            if (r) {
                var params = {"id": ids};
                $.post("/companyTarget/delete", params, function (data) {
                    if (data.code == 200) {
                        $.messager.alert('提示', '删除成功!', 'info',
                            function () {
                                $("#q-companyTarget-list").datagrid("reload");
                            });
                    }
                });
            }
        });
    }

    getCompanyTargetlistSelectionsIds = function () {
        var itemList = $("#q-companyTarget-list");
        var sels = itemList.datagrid("getSelections");
        var ids = [];
        for (var i in sels) {
            ids.push(sels[i].id);
        }
        ids = ids.join(",");
        return ids;
    }

</script>
