CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS account
(
    id
    BIGINT
    NOT
    NULL,
    name
    VARCHAR
(
    255
),
    dollar INTEGER,
    cents INTEGER,
    on_budget BOOLEAN,
    order_position INTEGER,
    created_date TIMESTAMP WITHOUT TIME ZONE,
    updated_date TIMESTAMP
                           WITHOUT TIME ZONE,
    user_user_id BIGINT,
    budget_budget_id BIGINT,
    CONSTRAINT pk_account PRIMARY KEY
(
    id
)
    );

CREATE TABLE IF NOT EXISTS budget
(
    budget_id
    BIGINT
    NOT
    NULL,
    name
    VARCHAR
(
    255
),
    user_user_id BIGINT,
    created_date TIMESTAMP WITHOUT TIME ZONE,
    updated_date TIMESTAMP
                           WITHOUT TIME ZONE,
    CONSTRAINT pk_budget PRIMARY KEY
(
    budget_id
)
    );

CREATE TABLE IF NOT EXISTS budget_budget_table_list
(
    budget_budget_id
    BIGINT
    NOT
    NULL,
    budget_table_list_id
    BIGINT
    NOT
    NULL
);

CREATE TABLE IF NOT EXISTS budget_table
(
    id
    BIGINT
    NOT
    NULL,
    user_id
    BIGINT,
    budget_budget_id
    BIGINT,
    month
    VARCHAR
(
    255
),
    year VARCHAR
(
    255
),
    created_date TIMESTAMP WITHOUT TIME ZONE,
    updated_date TIMESTAMP
                           WITHOUT TIME ZONE,
    notes VARCHAR
(
    255
),
    CONSTRAINT pk_budgettable PRIMARY KEY
(
    id
)
    );

CREATE TABLE IF NOT EXISTS budget_table_category_list
(
    budget_table_id
    BIGINT
    NOT
    NULL,
    category_list_id
    BIGINT
    NOT
    NULL
);

CREATE TABLE IF NOT EXISTS category
(
    id
    BIGINT
    NOT
    NULL,
    name
    VARCHAR
(
    255
),
    dollar_assigned INTEGER,
    cents_assigned INTEGER,
    dollar_activity INTEGER,
    cents_activity INTEGER,
    dollar_available INTEGER,
    cents_available INTEGER,
    main_order INTEGER,
    sub_order INTEGER,
    is_credit_card BOOLEAN,
    created_date TIMESTAMP WITHOUT TIME ZONE,
    updated_date TIMESTAMP
                           WITHOUT TIME ZONE,
    notes VARCHAR
(
    255
),
    user_user_id BIGINT,
    budget_table_id BIGINT,
    CONSTRAINT pk_category PRIMARY KEY
(
    id
)
    );

CREATE TABLE IF NOT EXISTS category_transaction_list
(
    category_id
    BIGINT
    NOT
    NULL,
    transaction_list_id
    BIGINT
    NOT
    NULL
);

CREATE TABLE IF NOT EXISTS payee
(
    id
    BIGINT
    NOT
    NULL,
    name
    VARCHAR
(
    255
),
    created_date TIMESTAMP WITHOUT TIME ZONE,
    updated_date TIMESTAMP
                           WITHOUT TIME ZONE,
    user_user_id BIGINT,
    CONSTRAINT pk_payee PRIMARY KEY
(
    id
)
    );

CREATE TABLE IF NOT EXISTS payee_transaction_list
(
    payee_id
    BIGINT
    NOT
    NULL,
    transaction_list_id
    BIGINT
    NOT
    NULL
);

CREATE TABLE IF NOT EXISTS refresh_token
(
    id
    BIGINT
    NOT
    NULL,
    user_user_id
    BIGINT,
    token
    VARCHAR
(
    255
) NOT NULL,
    expiry_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_refreshtoken PRIMARY KEY
(
    id
)
    );

CREATE TABLE IF NOT EXISTS role
(
    id
    INTEGER
    GENERATED
    BY
    DEFAULT AS
    IDENTITY
    NOT
    NULL,
    name
    VARCHAR
(
    20
),
    CONSTRAINT pk_role PRIMARY KEY
(
    id
)
    );

CREATE TABLE IF NOT EXISTS transaction
(
    id
    BIGINT
    NOT
    NULL,
    name
    VARCHAR
(
    255
),
    transaction_date date,
    note VARCHAR
(
    255
),
    dollar INTEGER,
    cents INTEGER,
    is_outflow BOOLEAN,
    is_pending BOOLEAN,
    created_date TIMESTAMP WITHOUT TIME ZONE,
    updated_date TIMESTAMP
                           WITHOUT TIME ZONE,
    account_id BIGINT,
    user_user_id BIGINT,
    payee_id BIGINT,
    budget_budget_id BIGINT,
    CONSTRAINT pk_transaction PRIMARY KEY
(
    id
)
    );

CREATE TABLE IF NOT EXISTS transaction_category_list
(
    transaction_id
    BIGINT
    NOT
    NULL,
    category_list_id
    BIGINT
    NOT
    NULL
);

CREATE TABLE IF NOT EXISTS user_profile
(
    user_id
    BIGINT
    NOT
    NULL,
    username
    VARCHAR
(
    255
),
    first_name VARCHAR
(
    255
),
    last_name VARCHAR
(
    255
),
    email VARCHAR
(
    255
),
    password VARCHAR
(
    255
),
    active_budget BIGINT,
    currency VARCHAR
(
    255
),
    currency_format VARCHAR
(
    255
),
    date_format VARCHAR
(
    255
),
    created_date TIMESTAMP WITHOUT TIME ZONE,
    updated_date TIMESTAMP
                           WITHOUT TIME ZONE,
    enabled BOOLEAN,
    CONSTRAINT pk_user_profile PRIMARY KEY
(
    user_id
)
    );

CREATE TABLE IF NOT EXISTS user_profile_payee_list
(
    user_user_id
    BIGINT
    NOT
    NULL,
    payee_list_id
    BIGINT
    NOT
    NULL
);

CREATE TABLE IF NOT EXISTS user_profile_transaction_list
(
    user_user_id
    BIGINT
    NOT
    NULL,
    transaction_list_id
    BIGINT
    NOT
    NULL
);

CREATE TABLE IF NOT EXISTS user_roles
(
    role_id
    INTEGER
    NOT
    NULL,
    user_id
    BIGINT
    NOT
    NULL,
    CONSTRAINT
    pk_user_roles
    PRIMARY
    KEY
(
    role_id,
    user_id
)
    );

ALTER TABLE user_profile
    ADD CONSTRAINT uc_2ed7c7100d6fe20b3639375fc UNIQUE (email);

ALTER TABLE user_profile
    ADD CONSTRAINT uc_881b66c910a10d6b99003a0eb UNIQUE (username);

ALTER TABLE budget_budget_table_list
    ADD CONSTRAINT uc_budget_budget_table_list_budgettablelist UNIQUE (budget_table_list_id);

ALTER TABLE budget_table_category_list
    ADD CONSTRAINT uc_budget_table_category_list_categorylist UNIQUE (category_list_id);

ALTER TABLE payee_transaction_list
    ADD CONSTRAINT uc_payee_transaction_list_transactionlist UNIQUE (transaction_list_id);

ALTER TABLE refresh_token
    ADD CONSTRAINT uc_refreshtoken_token UNIQUE (token);

ALTER TABLE user_profile_payee_list
    ADD CONSTRAINT uc_user_profile_payee_list_payeelist UNIQUE (payee_list_id);

ALTER TABLE user_profile_transaction_list
    ADD CONSTRAINT uc_user_profile_transaction_list_transactionlist UNIQUE (transaction_list_id);

ALTER TABLE account
    ADD CONSTRAINT FK_ACCOUNT_ON_BUDGET_BUDGETID FOREIGN KEY (budget_budget_id) REFERENCES budget (budget_id);

ALTER TABLE account
    ADD CONSTRAINT FK_ACCOUNT_ON_USER_USERID FOREIGN KEY (user_user_id) REFERENCES user_profile (user_id);

ALTER TABLE budget_table
    ADD CONSTRAINT FK_BUDGETTABLE_ON_BUDGET_BUDGETID FOREIGN KEY (budget_budget_id) REFERENCES budget (budget_id);

ALTER TABLE budget_table
    ADD CONSTRAINT FK_BUDGETTABLE_ON_USERID FOREIGN KEY (user_id) REFERENCES user_profile (user_id);

ALTER TABLE budget
    ADD CONSTRAINT FK_BUDGET_ON_USER_USERID FOREIGN KEY (user_user_id) REFERENCES user_profile (user_id);

ALTER TABLE category
    ADD CONSTRAINT FK_CATEGORY_ON_BUDGETTABLE FOREIGN KEY (budget_table_id) REFERENCES budget_table (id);

ALTER TABLE category
    ADD CONSTRAINT FK_CATEGORY_ON_USER_USERID FOREIGN KEY (user_user_id) REFERENCES user_profile (user_id);

ALTER TABLE payee
    ADD CONSTRAINT FK_PAYEE_ON_USER_USERID FOREIGN KEY (user_user_id) REFERENCES user_profile (user_id);

ALTER TABLE refresh_token
    ADD CONSTRAINT FK_REFRESHTOKEN_ON_USER_USERID FOREIGN KEY (user_user_id) REFERENCES user_profile (user_id);

ALTER TABLE transaction
    ADD CONSTRAINT FK_TRANSACTION_ON_ACCOUNT FOREIGN KEY (account_id) REFERENCES account (id);

ALTER TABLE transaction
    ADD CONSTRAINT FK_TRANSACTION_ON_BUDGET_BUDGETID FOREIGN KEY (budget_budget_id) REFERENCES budget (budget_id);

ALTER TABLE transaction
    ADD CONSTRAINT FK_TRANSACTION_ON_PAYEE FOREIGN KEY (payee_id) REFERENCES payee (id);

ALTER TABLE transaction
    ADD CONSTRAINT FK_TRANSACTION_ON_USER_USERID FOREIGN KEY (user_user_id) REFERENCES user_profile (user_id);

ALTER TABLE budget_budget_table_list
    ADD CONSTRAINT fk_budbudtablis_on_budget FOREIGN KEY (budget_budget_id) REFERENCES budget (budget_id);

ALTER TABLE budget_budget_table_list
    ADD CONSTRAINT fk_budbudtablis_on_budget_table FOREIGN KEY (budget_table_list_id) REFERENCES budget_table (id);

ALTER TABLE budget_table_category_list
    ADD CONSTRAINT fk_budtabcatlis_on_budget_table FOREIGN KEY (budget_table_id) REFERENCES budget_table (id);

ALTER TABLE budget_table_category_list
    ADD CONSTRAINT fk_budtabcatlis_on_category FOREIGN KEY (category_list_id) REFERENCES category (id);

ALTER TABLE category_transaction_list
    ADD CONSTRAINT fk_cattralis_on_category FOREIGN KEY (category_id) REFERENCES category (id);

ALTER TABLE category_transaction_list
    ADD CONSTRAINT fk_cattralis_on_transaction FOREIGN KEY (transaction_list_id) REFERENCES transaction (id);

ALTER TABLE payee_transaction_list
    ADD CONSTRAINT fk_paytralis_on_payee FOREIGN KEY (payee_id) REFERENCES payee (id);

ALTER TABLE payee_transaction_list
    ADD CONSTRAINT fk_paytralis_on_transaction FOREIGN KEY (transaction_list_id) REFERENCES transaction (id);

ALTER TABLE transaction_category_list
    ADD CONSTRAINT fk_tracatlis_on_category FOREIGN KEY (category_list_id) REFERENCES category (id);

ALTER TABLE transaction_category_list
    ADD CONSTRAINT fk_tracatlis_on_transaction FOREIGN KEY (transaction_id) REFERENCES transaction (id);

ALTER TABLE user_profile_payee_list
    ADD CONSTRAINT fk_usepropaylis_on_payee FOREIGN KEY (payee_list_id) REFERENCES payee (id);

ALTER TABLE user_profile_payee_list
    ADD CONSTRAINT fk_usepropaylis_on_user FOREIGN KEY (user_user_id) REFERENCES user_profile (user_id);

ALTER TABLE user_profile_transaction_list
    ADD CONSTRAINT fk_useprotralis_on_transaction FOREIGN KEY (transaction_list_id) REFERENCES transaction (id);

ALTER TABLE user_profile_transaction_list
    ADD CONSTRAINT fk_useprotralis_on_user FOREIGN KEY (user_user_id) REFERENCES user_profile (user_id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (role_id) REFERENCES role (id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES user_profile (user_id);

INSERT INTO role(name)
SELECT 'ROLE_USER' WHERE NOT EXISTS (SELECT * FROM role WHERE name = 'ROLE_USER');
INSERT INTO role(name)
SELECT 'ROLE_SUPERUSER' WHERE NOT EXISTS (SELECT * FROM role WHERE name = 'ROLE_SUPERUSER');
INSERT INTO role(name)
SELECT 'ROLE_ADMIN' WHERE NOT EXISTS (SELECT * FROM role WHERE name = 'ROLE_ADMIN');