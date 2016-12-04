# Deleting all menus
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE `bonappetit-local`.menu;
TRUNCATE TABLE `bonappetit-local`.item;
TRUNCATE TABLE `bonappetit-local`.item_option;
TRUNCATE TABLE `bonappetit-local`.radio_item;
SET FOREIGN_KEY_CHECKS = 1;

# Delete all orders
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE `bonappetit-local`.option_order;
TRUNCATE TABLE `bonappetit-local`.item_order;
SET FOREIGN_KEY_CHECKS = 1;


# Drop all tables! Use with care!!!!!!!!11111!!!!111einself!!!
SET FOREIGN_KEY_CHECKS = 0;
drop table `bonappetit-local`.item;
drop table `bonappetit-local`.item_option;
drop table `bonappetit-local`.item_order;
drop table `bonappetit-local`.item_side_dish;
drop table `bonappetit-local`.menu;
drop table `bonappetit-local`.menu_config;
drop table `bonappetit-local`.option_order;
drop table `bonappetit-local`.radio_item;
drop table `bonappetit-local`.schema_version;
drop table `bonappetit-local`.staff_member;
SET FOREIGN_KEY_CHECKS = 1;
