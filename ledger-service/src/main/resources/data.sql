INSERT INTO accounts(id, account_number, balance, currency, holder_name)
VALUES
(101,'6748781873',1000,'INR','Shrajan Jain')
ON CONFLICT (id) DO NOTHING;

INSERT INTO accounts(id, account_number, balance, currency, holder_name)
VALUES
(102, '915789758984',2000,'INR','Prashant Singh')
ON CONFLICT (id) DO NOTHING;