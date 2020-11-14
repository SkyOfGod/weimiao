ALTER TABLE `company`
MODIFY COLUMN `total_equity`  bigint(20) NOT NULL COMMENT '现总股本' AFTER `category`;

ALTER TABLE `consolidated_assets_liabilities`
ADD COLUMN `total_equity`  bigint(20) NOT NULL DEFAULT 0 COMMENT '当期总股本' AFTER `payable_salary`;

ALTER TABLE `combine_cash_flow`
MODIFY COLUMN `bonus_cash`  bigint(20) NOT NULL DEFAULT 0 COMMENT '分配股利、利润或偿付利息支付的现金' AFTER `cash_and_cash_equivalents_end`;



