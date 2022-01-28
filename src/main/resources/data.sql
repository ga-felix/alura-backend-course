INSERT INTO USER(name, email, password) VALUES('Aluno', 'aluno@email.com', '$2a$12$5YSQo31p/6fdzDt3aZm1O.pzdRu0R5Px.XjLWQRG7zdOxWGAQC4YS');

INSERT INTO COURSE(name, category) VALUES('Spring Boot', 'Programação');
INSERT INTO COURSE(name, category) VALUES('HTML 5', 'Front-end');

INSERT INTO TOPIC(title, message, created_at, status, author_id, course_id) VALUES('Dúvida', 'Erro ao criar projeto', '2019-05-05 18:00:00', 'NOT_ANSWERED', 1, 1);
INSERT INTO TOPIC(title, message, created_at, status, author_id, course_id) VALUES('Dúvida 2', 'Projeto não compila', '2019-05-05 19:00:00', 'NOT_ANSWERED', 1, 1);
INSERT INTO TOPIC(title, message, created_at, status, author_id, course_id) VALUES('Dúvida 3', 'Tag HTML', '2019-05-05 20:00:00', 'NOT_ANSWERED', 1, 2);