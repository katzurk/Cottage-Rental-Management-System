-- inital data
------ cottages

select next value for cottage_seq;
insert into cottage
    (id, name, address, size, bathrooms_number, max_people_num, rooms_number, price, owner_id)
values
    (1, 'micro cottage', 'mini 5', 23, 1, 1, 2, 10, 1);

select next value for cottage_seq;
insert into cottage
    (id, name, address, size, bathrooms_number, max_people_num, rooms_number, price, owner_id)
values
    (2, 'the Grand Villa', 'epic 20', 600, 100, 50, 100, 120000, 1);

------- users
select next value for login_seq;
insert into login
    (id, username, password)
values
    (1, 'Jan', 'Kowalski');

select next value for login_seq;
insert into login
    (id, username, password)
values
    (2, 'w', '1');
