INSERT INTO public.address(
	id, city, country, "number", street)
	VALUES (1, 'Žabalj', 'Srbija', '26', 'Moja lepa ulica');
INSERT INTO public.address(
	id, city, country, "number", street)
	VALUES (2, 'Niš', 'Srbija', '9', 'Niška ulica');
INSERT INTO public.address(
	id, city, country, "number", street)
	VALUES (3, 'Subotica', 'Srbija', '20', 'Subotička ulica');
INSERT INTO public.address(
	id, city, country, "number", street)
	VALUES (4, 'Beograd', 'Srbija', '18', 'Beogradska ulica');
INSERT INTO public.address(
	id, city, country, "number", street)
	VALUES (5, 'Novi Sad', 'Srbija', '21', 'Novosadska ulica');

INSERT INTO public.work_time(
	id, end_time, start_time)
	VALUES (1, '21:00', '08:00');
INSERT INTO public.work_time(
	id, end_time, start_time)
	VALUES (2, '17:00', '06:00');
INSERT INTO public.work_time(
	id, end_time, start_time)
	VALUES (3, '20:00', '09:00');
INSERT INTO public.work_time(
	id, end_time, start_time)
	VALUES (4, '18:00', '07:00');
INSERT INTO public.work_time(
	id, end_time, start_time)
	VALUES (5, '19:00', '05:00');

INSERT INTO public.blood_bank(
	id, average_rating, description, is_deleted, name, address_id, worktime_id)
	VALUES (1, 4.5, 'Prava banka krvi za vas', false, 'Krv na dar', 1, 1);

INSERT INTO public.blood_bank(
	id, average_rating, description, is_deleted, name, address_id, worktime_id)
	VALUES (2, 3, 'dobra banka najboljaaaaaa', false, 'Daj mi krv', 2, 2);

INSERT INTO public.blood_bank(
	id, average_rating, description, is_deleted, name, address_id, worktime_id)
	VALUES (3, 4.2, 'Vrhunska usluga samo za Vas!', false, 'Krv+', 3, 3);

INSERT INTO public.blood_bank(
	id, average_rating, description, is_deleted, name, address_id, worktime_id)
	VALUES (4, 3.8, 'Brzo i efikasno! Jer smo najbolji.', false, 'KrvExpress', 4, 4);

INSERT INTO public.blood_bank(
	id, average_rating, description, is_deleted, name, address_id, worktime_id)
	VALUES (5, 1.5, 'Losi smo, al smo takodje i skupi', false, 'PropalaBanka', 5, 5);


