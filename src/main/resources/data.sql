INSERT INTO banks (bank_name, logo) VALUES ('PKO BP', 'logopko.jpg');
INSERT INTO banks (bank_name, logo) VALUES ('Pekao SA', 'logopekao.jpg');
INSERT INTO banks (bank_name, logo) VALUES ('Millennium', 'logomillennium.jpg');
INSERT INTO banks (bank_name, logo) VALUES ('mBank', 'logombank.jpg');
INSERT INTO banks (bank_name, logo) VALUES ('BNP', 'logobnp.jpg');

INSERT INTO agencies (agency_name, city, email, hours, phone, street, street_number, zip_code, bank_id) VALUES ('1 Oddział w Katowicach', 'Katowice', '1kat@pko.pl', '9:00 - 17:00', '322003300', 'Katowicka', '5A', '40-000', 1);
INSERT INTO agencies (agency_name, city, email, hours, phone, street, street_number, zip_code, bank_id) VALUES ('2 Oddział w Katowicach', 'Katowice', '2kat@pko.pl', '10:00 - 18:00', '322004400', 'Gliwicka', '122', '40-001', 1);
INSERT INTO agencies (agency_name, city, email, hours, phone, street, street_number, zip_code, bank_id) VALUES ('1 Oddział w Katowicach', 'Katowice', '1kat@pekao.pl', '10:00 - 18:00', '323004400', 'Opolska', '1', '40-101', 2);
INSERT INTO agencies (agency_name, city, email, hours, phone, street, street_number, zip_code, bank_id) VALUES ('1 Oddział w Katowicach', 'Katowice', '1kat@millennium.pl', '9:30 - 17:30', '324504400', 'Kościuszki', '23', '40-201', 3);

INSERT INTO loans (credit_rate, insurance, max_borrower_age, max_credit_amount, max_credit_period, min_borrower_age, min_credit_amount, offer, service_charge, bank_id) VALUES (8.58, 2.0, 70, 100000, 120, 18, 5000, 'Zima', 2.50, 1);
INSERT INTO loans (credit_rate, insurance, max_borrower_age, max_credit_amount, max_credit_period, min_borrower_age, min_credit_amount, offer, service_charge, bank_id) VALUES (9.58, 0.0, 80, 200000, 96, 18, 15000, 'Zimowa', 9.50, 2);
INSERT INTO loans (credit_rate, insurance, max_borrower_age, max_credit_amount, max_credit_period, min_borrower_age, min_credit_amount, offer, service_charge, bank_id) VALUES (7.58, 0.0, 75, 150000, 96, 18, 10000, 'Najlepsza', 6.50, 3);
INSERT INTO loans (credit_rate, insurance, max_borrower_age, max_credit_amount, max_credit_period, min_borrower_age, min_credit_amount, offer, service_charge, bank_id) VALUES (9.68, 1.0, 70, 150000, 84, 18, 10000, 'Jedyna', 5.50, 4);
INSERT INTO loans (credit_rate, insurance, max_borrower_age, max_credit_amount, max_credit_period, min_borrower_age, min_credit_amount, offer, service_charge, bank_id) VALUES (9.88, 0.0, 70, 160000, 120, 18, 20000, 'Wspaniała', 0.0, 5);
INSERT INTO loans (credit_rate, insurance, max_borrower_age, max_credit_amount, max_credit_period, min_borrower_age, min_credit_amount, offer, service_charge, bank_id) VALUES (8.88, 3.0, 70, 160000, 120, 18, 20000, 'Super', 0.0, 2);

INSERT INTO mortgages (contribution_percent, credit_rate, insurance, max_borrower_age, max_credit_amount, max_credit_period, min_borrower_age, min_credit_amount, offer, service_charge, bank_id) VALUES (10.0, 3.58, 0.0, 80, 2000000, 360, 18, 50000, 'Zimowa', 0.50, 3);
INSERT INTO mortgages (contribution_percent, credit_rate, insurance, max_borrower_age, max_credit_amount, max_credit_period, min_borrower_age, min_credit_amount, offer, service_charge, bank_id) VALUES (20.0, 3.78, 0.0, 70, 2000000, 360, 18, 50000, 'Wiosna', 1.50, 4);