
CREATE TABLE public.userrole (
  id INT NOT NULL,
  name VARCHAR(45) NULL,
  PRIMARY KEY (id));

INSERT INTO public.userrole (id, name) VALUES ('0', 'ROLE_USER');
INSERT INTO public.userrole (id, name) VALUES ('1', 'ROLE_ADMIN');