INSERT INTO public.address(
	city, country, number, street, latitude, longitude)
	VALUES ('Novi Sad', 'Srbija', '10', 'Hajduk Veljkova', '45.2456888', '19.8321409');
INSERT INTO public.address(
	city, country, number, street, latitude, longitude)
	VALUES ('Novi Sad', 'Srbija', '34', 'Vršačka', '45.2448428', '19.8264761');
INSERT INTO public.address(
	city, country, number, street, latitude, longitude)
	VALUES ('Novi Sad', 'Srbija', '16', 'Jevrejska', '45.251913', '19.8275537');
INSERT INTO public.address(
	city, country, number, street, latitude, longitude)
	VALUES ('Novi Sad', 'Srbija', '73a', 'Narodnog Fronta', '45.2440572', '19.8305101');
INSERT INTO public.address(
	city, country, number, street, latitude, longitude)
	VALUES ('Novi Sad', 'Srbija', '3', 'Hajduk Veljkova', '45.2545492', '19.8238609');
INSERT INTO public.address(
	city, country, number, street, latitude, longitude)
	VALUES ('Sremska Kamenica', 'Srbija', '1', 'Stevana Goldmana', '45.2084196', '19.827876');

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
	VALUES (false, 'Has it been less than 7 days since the last tooth extraction and minor dental interventions?', NULL);
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
	gender, jmbg, name, phone_number, profession, profession_info, surname, collected_points, penalties, user_type, account_id, address_id)
	VALUES ('MALE', '0123456789667', 'Miloš', '064/52-24-213', 'Software Engineer', 'WeDoSoftware', 'Zeljko', 50, 0, 3, 2, 6);


INSERT INTO public.api_key(
    api_key_code, is_valid)
VALUES ('e67553a6-1b8e-48af-a393-cd9603ac1b29', 'true');

INSERT INTO public.equipment(
    amount, equipment_type, blood_bank_id)
VALUES ( 500, 'VIALS', 1);
INSERT INTO public.equipment(
    amount, equipment_type, blood_bank_id)
VALUES ( 1000, 'BLOOD_GROUPING_REAGENTS', 1);
INSERT INTO public.equipment(
    amount, equipment_type, blood_bank_id)
VALUES ( 500, 'BLOOD_BAGS', 1);
INSERT INTO public.equipment(
    amount, equipment_type, blood_bank_id)
VALUES ( 1000, 'BLOOD_COLLECTION_TUBES', 1);
INSERT INTO public.equipment(
    amount, equipment_type, blood_bank_id)
VALUES ( 1000, 'BLOOD_FILTERS', 1);
INSERT INTO public.equipment(
    amount, equipment_type, blood_bank_id)
VALUES ( 1000, 'BLOOD_COLLECTION_NEEDLES', 1);


INSERT INTO public.blood_sample(
    amount, blood_type, blood_bank_id, delivery_id)
VALUES ( 2000, 'A_POS', 1, null);
INSERT INTO public.blood_sample(
    amount, blood_type, blood_bank_id, delivery_id)
VALUES (2000, 'A_NEG', 1, null);
INSERT INTO public.blood_sample(
    amount, blood_type, blood_bank_id, delivery_id)
VALUES (2500, 'B_POS', 1, null);
INSERT INTO public.blood_sample(
     amount, blood_type, blood_bank_id, delivery_id)
VALUES (3000, 'B_NEG', 1, null);
INSERT INTO public.blood_sample(
     amount, blood_type, blood_bank_id, delivery_id)
VALUES ( 1500, 'AB_POS', 1, null);
INSERT INTO public.blood_sample(
    amount, blood_type, blood_bank_id, delivery_id)
VALUES ( 1500, 'AB_NEG', 1, null);
INSERT INTO public.blood_sample(
    amount, blood_type, blood_bank_id, delivery_id)
VALUES (2000, 'ZERO_NEG', 1, null);
INSERT INTO public.blood_sample(
    amount, blood_type, blood_bank_id, delivery_id)
VALUES (2000, 'ZERO_POS', 1, null);


INSERT INTO public.account(
	email, password, required_password_change)
	VALUES ( 'mihailoveljic3010@gmail.com', null, false);

INSERT INTO public.users(
	gender, jmbg, name, phone_number, profession, profession_info, surname, collected_points, penalties, user_type, account_id, address_id)
	VALUES ( null, '1231235675678', 'Mihailo', '060-11-22-33', null, null, 'Veljić', 50, 0, 0, 3, null);

INSERT INTO public.appointment(
	date, description, duration, price, status, blood_bank_id, user_email)
	VALUES ('2022-12-12 12:00:00', 'Test termin 1', 30, 5000, 'FREE', 1, NULL);
INSERT INTO public.appointment(
	date, description, duration, price, status, blood_bank_id, user_email)
	VALUES ('2022-12-26 15:00:00', 'Test termin 2', 60, 10000, 'FREE', 2, NULL);
INSERT INTO public.appointment(
	date, description, duration, price, status, blood_bank_id, user_email, QR)
	VALUES ('2022-12-23 17:00:00', 'Moj termin 1', 60, 750, 'DONE', 1, 'mihailoveljic3010@gmail.com', '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');
INSERT INTO public.appointment(
	date, description, duration, price, status, blood_bank_id, user_email, QR)
	VALUES ('2022-12-22 11:00:00', 'Moj termin 2', 60, 1000, 'DONE', 1, 'mihailoveljic3010@gmail.com', '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');
INSERT INTO public.appointment(
	date, description, duration, price, status, blood_bank_id, user_email, QR)
	VALUES ('2022-12-25 09:00:00', 'Moj termin 3', 60, 8000, 'DONE', 1, 'mihailoveljic3010@gmail.com', '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');
