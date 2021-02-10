
INSERT INTO gift_certificate
(id, name,description,price,duration,create_date,last_update_date) VALUES (1, 'SpaCertificate', 'SpaCertificate for 2 person', 100, 40, '2021-01-20 12:27:55', '2021-01-20 12:27:55');
INSERT INTO gift_certificate
(id, name,description,price,duration,create_date,last_update_date) VALUES (2, 'SportCertificate', 'SportCertificate for 4 person', 100, 40, '2021-01-20 12:27:55', '2021-01-20 12:27:55');
INSERT INTO gift_certificate
(id, name,description,price,duration,create_date,last_update_date) VALUES (3, 'DanceCertificate', 'DanceCertificate for 2 person', 100, 40, '2021-01-20 12:27:55', '2021-01-20 12:27:55');
INSERT INTO gift_certificate
(id, name,description,price,duration,create_date,last_update_date) VALUES (4, 'KidsCertificate', 'KidsCertificate for 2 person', 100, 40, '2021-01-20 12:27:55', '2021-01-20 12:27:55');


INSERT INTO tag (id, nameTag) VALUES (1, 'spa');
INSERT INTO tag (id, nameTag) VALUES (2, 'relax');
INSERT INTO tag (id, nameTag) VALUES (3, 'sport');
INSERT INTO tag (id, nameTag) VALUES (4, 'dance');
INSERT INTO tag (id, nameTag) VALUES (5, 'twist');
INSERT INTO tag (id, nameTag) VALUES (6, 'children');


INSERT INTO gift_certificate_has_tag (gift_certicicate_id_gift_certicicate, tag_id_tag) VALUES (1, 1);
INSERT INTO gift_certificate_has_tag (gift_certicicate_id_gift_certicicate, tag_id_tag) VALUES (1, 2);
INSERT INTO gift_certificate_has_tag (gift_certicicate_id_gift_certicicate, tag_id_tag) VALUES (2, 3);
INSERT INTO gift_certificate_has_tag (gift_certicicate_id_gift_certicicate, tag_id_tag) VALUES (3, 3);
INSERT INTO gift_certificate_has_tag (gift_certicicate_id_gift_certicicate, tag_id_tag) VALUES (3, 4);
INSERT INTO gift_certificate_has_tag (gift_certicicate_id_gift_certicicate, tag_id_tag) VALUES (3, 5);
INSERT INTO gift_certificate_has_tag (gift_certicicate_id_gift_certicicate, tag_id_tag) VALUES (4, 6);



