INSERT INTO public.address(
	id, city, country, number, street)
	VALUES (1, 'Žabalj', 'Srbija', '26', 'Moja lepa ulica');
INSERT INTO public.address(
	id, city, country, number, street)
	VALUES (2, 'Niš', 'Srbija', '9', 'Niška ulica');
INSERT INTO public.address(
	id, city, country, number, street)
	VALUES (3, 'Subotica', 'Srbija', '20', 'Subotička ulica');
INSERT INTO public.address(
	id, city, country, number, street)
	VALUES (4, 'Beograd', 'Srbija', '18', 'Beogradska ulica');
INSERT INTO public.address(
	id, city, country, number, street)
	VALUES (5, 'Novi Sad', 'Srbija', '21', 'Novosadska ulica');
INSERT INTO public.address(
	id, city, country, number, street)
	VALUES (6, 'Novi Sad', 'Srbija', '21', 'Nasa ulica');

INSERT INTO public.account(
    id, email, password, required_password_change)
VALUES (1, 'holja2208@gmail.com', '$2a$10$LEXs7TjyqHnCgD3uzEv6rOo9BDizvIVzCksQ6Rhf.aJH7eV2wni7C', false);
INSERT INTO public.account(
    id, email, password, required_password_change)
VALUES (2, 'miloszeljko00@gmail.com', '$2a$10$LEXs7TjyqHnCgD3uzEv6rOo9BDizvIVzCksQ6Rhf.aJH7eV2wni7C', false);

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

INSERT INTO public.question(
	id, acceptable_answer, text, for_gender)
	VALUES (1, false, 'Are you under 50kg?', NULL);
INSERT INTO public.question(
	id, acceptable_answer, text, for_gender)
	VALUES (2, false, 'Do you have symptoms of a cold, some illness or just not feeling well?', NULL);
INSERT INTO public.question(
	id, acceptable_answer, text, for_gender)
	VALUES (3, false, 'Have you noticed any changes in your skin (infections, rashes, fungal diseases)?', NULL);
INSERT INTO public.question(
	id, acceptable_answer, text, for_gender)
	VALUES (4, false, 'Do you suffer from high blood pressure?', NULL);
INSERT INTO public.question(
	id, acceptable_answer, text, for_gender)
	VALUES (5, false, 'Do you suffer from low blood pressure?', NULL);
INSERT INTO public.question(
	id, acceptable_answer, text, for_gender)
	VALUES (6, false, 'Are you under treatment or it has not been at least 7 days since last antibiotic treatment?', NULL);
INSERT INTO public.question(
	id, acceptable_answer, text, for_gender)
	VALUES (7, false, 'Are you in the phase of a regular monthly cycle?', 'FEMALE');
INSERT INTO public.question(
	id, acceptable_answer, text, for_gender)
	VALUES (8, false, 'Are you pregnant?', 'FEMALE');
INSERT INTO public.question(
	id, acceptable_answer, text, for_gender)
	VALUES (9, true, 'Has it been at least 7 days since the last tooth extraction and minor dental interventions?', NULL);
INSERT INTO public.question(
	id, acceptable_answer, text, for_gender)
	VALUES (10, false, 'Have you had a tattoo in the past 6 months?', NULL);
INSERT INTO public.question(
	id, acceptable_answer, text, for_gender)
	VALUES (11, false, 'Have you had a piercing in the past 6 months?', NULL);
INSERT INTO public.question(
	id, acceptable_answer, text, for_gender)
	VALUES (12, false, 'Have you had a surgical intervention or blood transfusion in the last 6 months?', NULL);

INSERT INTO public.staff(
    id, gender, jmbg, name , phone_number, profession, profession_info, surname, account_id, address_id, blood_bank_id)
VALUES (1, 'MALE', '0123456789666', 'Nikola', '061/12-34-567','Administrator centra', 'Menadzer', 'Holjevac', 1, 5, 1);

INSERT INTO public.users(
	id, gender, jmbg, name, phone_number, profession, profession_info, surname, collected_points, user_type, account_id, address_id)
	VALUES (2, 'MALE', '0123456789667', 'Miloš', '064/52-24-213', 'Software Engineer', 'WeDoSoftware', 'Zeljko', 50, 3, 2, 6);


INSERT INTO public.api_key(
    id, api_key_code, is_valid)
VALUES (1, 'e67553a6-1b8e-48af-a393-cd9603ac1b29', 'true');

INSERT INTO public.blood_sample(
    id, amount, blood_type, blood_bank_id, delivery_id)
VALUES (1, 500, 'A_POSITIVE', 1, null);
INSERT INTO public.blood_sample(
    id, amount, blood_type, blood_bank_id, delivery_id)
VALUES (2, 200, 'A_NEGATIVE', 1, null);
INSERT INTO public.blood_sample(
    id, amount, blood_type, blood_bank_id, delivery_id)
VALUES (3, 300, 'A_POSITIVE', 2, null);
INSERT INTO public.account(
	id, email, password, required_password_change)
	VALUES (3, 'mihailoveljic3010@gmail.com', null, false);

INSERT INTO public.users(
	id, gender, jmbg, name, phone_number, profession, profession_info, surname, collected_points, user_type, account_id, address_id)
	VALUES (1, null, null, null, null, null, null, null, 50, 0, 3, null);
