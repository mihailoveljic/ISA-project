INSERT INTO public.address(
	city, country, number, street)
	VALUES ('Žabalj', 'Srbija', '26', 'Moja lepa ulica');
INSERT INTO public.address(
	city, country, number, street)
	VALUES ('Niš', 'Srbija', '9', 'Niška ulica');
INSERT INTO public.address(
	city, country, number, street)
	VALUES ('Subotica', 'Srbija', '20', 'Subotička ulica');
INSERT INTO public.address(
	city, country, number, street)
	VALUES ('Beograd', 'Srbija', '18', 'Beogradska ulica');
INSERT INTO public.address(
	city, country, number, street)
	VALUES ('Novi Sad', 'Srbija', '21', 'Novosadska ulica');
INSERT INTO public.address(
	city, country, number, street)
	VALUES ('Novi Sad', 'Srbija', '21', 'Nasa ulica');

INSERT INTO public.account(
    email, password, required_password_change)
VALUES ('holja2208@gmail.com', '$2a$10$LEXs7TjyqHnCgD3uzEv6rOo9BDizvIVzCksQ6Rhf.aJH7eV2wni7C', false);
INSERT INTO public.account(
    email, password, required_password_change)
VALUES ('miloszeljko00@gmail.com', '$2a$10$LEXs7TjyqHnCgD3uzEv6rOo9BDizvIVzCksQ6Rhf.aJH7eV2wni7C', false);

INSERT INTO public.work_time(
	end_time, start_time)
	VALUES ('21:00', '08:00');
INSERT INTO public.work_time(
	end_time, start_time)
	VALUES ('17:00', '06:00');
INSERT INTO public.work_time(
	end_time, start_time)
	VALUES ('20:00', '09:00');
INSERT INTO public.work_time(
	end_time, start_time)
	VALUES ('18:00', '07:00');
INSERT INTO public.work_time(
	end_time, start_time)
	VALUES ('19:00', '05:00');

INSERT INTO public.blood_bank(
	average_rating, description, is_deleted, name, address_id, worktime_id)
	VALUES (4.5, 'Prava banka krvi za vas', false, 'Krv na dar', 1, 1);

INSERT INTO public.blood_bank(
	average_rating, description, is_deleted, name, address_id, worktime_id)
	VALUES (3, 'dobra banka najboljaaaaaa', false, 'Daj mi krv', 2, 2);

INSERT INTO public.blood_bank(
	average_rating, description, is_deleted, name, address_id, worktime_id)
	VALUES (4.2, 'Vrhunska usluga samo za Vas!', false, 'Krv+', 3, 3);

INSERT INTO public.blood_bank(
	average_rating, description, is_deleted, name, address_id, worktime_id)
	VALUES (3.8, 'Brzo i efikasno! Jer smo najbolji.', false, 'KrvExpress', 4, 4);

INSERT INTO public.blood_bank(
	average_rating, description, is_deleted, name, address_id, worktime_id)
	VALUES (1.5, 'Losi smo, al smo takodje i skupi', false, 'PropalaBanka', 5, 5);

INSERT INTO public.question(
	acceptable_answer, text, for_gender)
	VALUES (false, 'Are you under 50kg?', NULL);
INSERT INTO public.question(
	acceptable_answer, text, for_gender)
	VALUES (false, 'Do you have symptoms of a cold, some illness or just not feeling well?', NULL);
INSERT INTO public.question(
	acceptable_answer, text, for_gender)
	VALUES (false, 'Have you noticed any changes in your skin (infections, rashes, fungal diseases)?', NULL);
INSERT INTO public.question(
	acceptable_answer, text, for_gender)
	VALUES ( false, 'Do you suffer from high blood pressure?', NULL);
INSERT INTO public.question(
	acceptable_answer, text, for_gender)
	VALUES ( false, 'Do you suffer from low blood pressure?', NULL);
INSERT INTO public.question(
	acceptable_answer, text, for_gender)
	VALUES (false, 'Are you under treatment or it has not been at least 7 days since last antibiotic treatment?', NULL);
INSERT INTO public.question(
	acceptable_answer, text, for_gender)
	VALUES (false, 'Are you in the phase of a regular monthly cycle?', 'FEMALE');
INSERT INTO public.question(
	acceptable_answer, text, for_gender)
	VALUES (false, 'Are you pregnant?', 'FEMALE');
INSERT INTO public.question(
	acceptable_answer, text, for_gender)
	VALUES (true, 'Has it been at least 7 days since the last tooth extraction and minor dental interventions?', NULL);
INSERT INTO public.question(
	acceptable_answer, text, for_gender)
	VALUES (false, 'Have you had a tattoo in the past 6 months?', NULL);
INSERT INTO public.question(
	acceptable_answer, text, for_gender)
	VALUES (false, 'Have you had a piercing in the past 6 months?', NULL);
INSERT INTO public.question(
	acceptable_answer, text, for_gender)
	VALUES (false, 'Have you had a surgical intervention or blood transfusion in the last 6 months?', NULL);

INSERT INTO public.staff(
    gender, jmbg, name , phone_number, profession, profession_info, surname, account_id, address_id, blood_bank_id)
VALUES ('MALE', '0123456789666', 'Nikola', '061/12-34-567','Administrator centra', 'Menadzer', 'Holjevac', 1, 5, 1);
INSERT INTO public.staff(
    gender, jmbg, name , phone_number, profession, profession_info, surname, account_id, address_id, blood_bank_id)
VALUES ('MALE', '0011223344556', 'Pera', '069/11-22-333','Zdravstveni radnik', 'Medicinski brat', 'Peric', null, 4, 1);
INSERT INTO public.staff(
    gender, jmbg, name , phone_number, profession, profession_info, surname, account_id, address_id, blood_bank_id)
VALUES ('FEMALE', '1357902468007', 'Marija', '061/11-29-222','Doktor', 'Specijalista', 'Maric', null, 3, 1);



INSERT INTO public.users(
	gender, jmbg, name, phone_number, profession, profession_info, surname, collected_points, user_type, account_id, address_id)
	VALUES ('MALE', '0123456789667', 'Miloš', '064/52-24-213', 'Software Engineer', 'WeDoSoftware', 'Zeljko', 50, 3, 2, 6);


INSERT INTO public.api_key(
    api_key_code, is_valid)
VALUES ('e67553a6-1b8e-48af-a393-cd9603ac1b29', 'true');

INSERT INTO public.blood_sample(
    amount, blood_type, blood_bank_id, delivery_id)
VALUES ( 500, 'A_POSITIVE', 1, null);
INSERT INTO public.blood_sample(
    amount, blood_type, blood_bank_id, delivery_id)
VALUES (1200, 'A_NEGATIVE', 1, null);
INSERT INTO public.blood_sample(
    amount, blood_type, blood_bank_id, delivery_id)
VALUES (2500, 'B_POSITIVE', 1, null);
INSERT INTO public.blood_sample(
     amount, blood_type, blood_bank_id, delivery_id)
VALUES (2000, 'B_NEGATIVE', 1, null);
INSERT INTO public.blood_sample(
     amount, blood_type, blood_bank_id, delivery_id)
VALUES ( 1500, 'AB_POSITIVE', 1, null);
INSERT INTO public.blood_sample(
    amount, blood_type, blood_bank_id, delivery_id)
VALUES (700, 'O_NEGATIVE', 1, null);
INSERT INTO public.blood_sample(
    amount, blood_type, blood_bank_id, delivery_id)
VALUES ( 300, 'A_POSITIVE', 2, null);

INSERT INTO public.account(
	email, password, required_password_change)
	VALUES ( 'mihailoveljic3010@gmail.com', null, false);

INSERT INTO public.users(
	gender, jmbg, name, phone_number, profession, profession_info, surname, collected_points, user_type, account_id, address_id)
	VALUES ( null, '1231235675678', 'Mihailo', '060-11-22-33', null, null, 'Veljić', 50, 0, 3, null);
