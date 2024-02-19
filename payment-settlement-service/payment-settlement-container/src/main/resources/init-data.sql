INSERT INTO payment_settlement.schools(id, hour_price, name)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb21', 10.00, 'High school nr. 1');
INSERT INTO payment_settlement.schools(id, hour_price, name)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb22', 12.00, 'High school nr. 2');

INSERT INTO payment_settlement.parents(id, first_name, last_name)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb23', 'Martin', 'Loother');
INSERT INTO payment_settlement.parents(id, first_name, last_name)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb24', 'John', 'Kennedy');
INSERT INTO payment_settlement.parents(id, first_name, last_name)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb25', 'John', 'Travolta');
INSERT INTO payment_settlement.parents(id, first_name, last_name)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb26', 'Barack', 'Obama');

INSERT INTO payment_settlement.children(id, first_name, last_name, parent_id, school_id)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb27', 'Michelle', 'Obama', 'd215b5f8-0249-4dc5-89a3-51fd148cfb26', 'd215b5f8-0249-4dc5-89a3-51fd148cfb21');
INSERT INTO payment_settlement.child_attendances(id, entry_date, exit_date, child_id)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb32', '2022-10-10 11:30:30', '2022-10-10 12:30:30', 'd215b5f8-0249-4dc5-89a3-51fd148cfb27');
INSERT INTO payment_settlement.child_attendances(id, entry_date, exit_date, child_id)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb33', '2022-10-11 06:30:30', '2022-10-11 12:30:30', 'd215b5f8-0249-4dc5-89a3-51fd148cfb27');
INSERT INTO payment_settlement.child_attendances(id, entry_date, exit_date, child_id)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb34', '2022-10-20 05:30:30', '2022-10-20 12:00:00', 'd215b5f8-0249-4dc5-89a3-51fd148cfb27');
INSERT INTO payment_settlement.children(id, first_name, last_name, parent_id, school_id)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb28', 'Kendrick', 'Obama', 'd215b5f8-0249-4dc5-89a3-51fd148cfb26', 'd215b5f8-0249-4dc5-89a3-51fd148cfb21');
INSERT INTO payment_settlement.child_attendances(id, entry_date, exit_date, child_id)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb35', '2022-10-10 11:30:30', '2022-10-10 16:00:01', 'd215b5f8-0249-4dc5-89a3-51fd148cfb28');
INSERT INTO payment_settlement.child_attendances(id, entry_date, exit_date, child_id)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb36', '2022-11-11 06:30:30', '2022-11-11 12:30:30', 'd215b5f8-0249-4dc5-89a3-51fd148cfb28');
INSERT INTO payment_settlement.child_attendances(id, entry_date, exit_date, child_id)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb37', '2022-09-11 05:30:30', '2022-09-11 12:00:00', 'd215b5f8-0249-4dc5-89a3-51fd148cfb28');


INSERT INTO payment_settlement.children(id, first_name, last_name, parent_id, school_id)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb29', 'John', 'Kennedy', 'd215b5f8-0249-4dc5-89a3-51fd148cfb24', 'd215b5f8-0249-4dc5-89a3-51fd148cfb21');
INSERT INTO payment_settlement.children(id, first_name, last_name, parent_id, school_id)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb30', 'Andrew', 'Kennedy', 'd215b5f8-0249-4dc5-89a3-51fd148cfb24', 'd215b5f8-0249-4dc5-89a3-51fd148cfb21');

INSERT INTO payment_settlement.children(id, first_name, last_name, parent_id, school_id)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb31', 'Margaret', 'Loother', 'd215b5f8-0249-4dc5-89a3-51fd148cfb23', 'd215b5f8-0249-4dc5-89a3-51fd148cfb21');
INSERT INTO payment_settlement.child_attendances(id, entry_date, exit_date, child_id)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb38', '2022-10-01 06:01:00', '2022-10-01 12:30:30', 'd215b5f8-0249-4dc5-89a3-51fd148cfb31');
INSERT INTO payment_settlement.child_attendances(id, entry_date, exit_date, child_id)
	VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb39', '2022-10-25 05:30:30', '2022-10-25 12:30:30', 'd215b5f8-0249-4dc5-89a3-51fd148cfb31');