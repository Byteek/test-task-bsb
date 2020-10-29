
# Тестовое задание

1. Перед запуском приложения создайте базу данных testtaskdb (если у вас другое название, то поменяйте его в файле application.properties)

2. После создания БД перейдите в "Query tool" и введите следующие строки `SQL`:

```SQL
CREATE TABLE public.userrole (
  id INT NOT NULL,
  name VARCHAR(45) NULL,
  PRIMARY KEY (id));

INSERT INTO public.userrole (id, name) VALUES ('0', 'ROLE_USER');
INSERT INTO public.userrole (id, name) VALUES ('1', 'ROLE_ADMIN');
```

3. После этого запустите приложение 

Можете переходить по адресу http://localhost:8082/swagger-ui/#/  
