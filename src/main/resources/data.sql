INSERT INTO PUBLIC.USERS(FULLNAME, USERNAME, PASSWORD, ROLE)
VALUES ('Administrator', 'admin', '$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa', 'ADMIN'),
       ('User', 'user', '$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa', 'USER'),
       ('GioAdmin', 'Admin', '$2a$10$no1Bnc0L/Jq5WnNtRA8pOu85KJmSfgZCavocgseLLKaExr4ccXNZy', 'ADMIN'),
       ('GioUser', 'User', '$2a$10$i0PKMsYCLqV1nldoI6uM3ut8I9NIisPMJUHcfr6RPYYfyfVoEvWE2', 'USER'),
       ('userForTest', 'userForTest', '$2a$10$6X4gWIhEUoe/w.tX3sjO1OcCCaneAJxllOjNxDFQjjAYlVhMOdEGS', 'USER'),
       ('adminForTest ', 'adminForTest ', '$2a$10$2AwCI/q1h4XoyPV6c2V9auqiRvJGgI7gtlWVDzUVXZ1h1Ih6tWpeW', 'ADMIN');


INSERT INTO PUBLIC.BID (ASK, ASK_QUANTITY, BID, BID_QUANTITY, BID_LIST_DATE, CREATION_DATE,
                        REVISION_DATE, ACCOUNT, BENCHMARK, BOOK, COMMENTARY, CREATION_NAME, DEAL_NAME, DEAL_TYPE,
                        REVISION_NAME, SECURITY, SIDE, SOURCE_LIST_ID, STATUS, TRADER, TYPE)
VALUES (null, null, null, 1, null, null, null, 'Test Account A', null, null, null, null, null, null, null, null,
        null, null, null, null, 'Test Type A'),
       (null, null, null, 2, null, null, null, 'Test Account B', null, null, null, null, null, null, null, null,
        null, null, null, null, 'Test Type B'),
       (null, null, null, 2, null, null, null, 'Test Account C', null, null, null, null, null, null, null, null,
        null, null, null, null, 'Test Type C'),
       (null, null, null, 2, null, null, null, 'Test Account D', null, null, null, null, null, null, null, null,
        null, null, null, null, 'Test Type D');


INSERT INTO PUBLIC.CURVEPOINT (CURVE_ID, TERM, "value", AS_OF_DATE, CREATION_DATE)
VALUES (1, 10, 1, null, null),
       (4, 20, 2, null, null),
       (8, 5, 5, null, null);


INSERT INTO PUBLIC.RATING (ORDER_NUMBER, FITCH_RATING, MOODYS_RATING, SANDPRATING)
VALUES (1, '1', '1', '1'),
       (2, '2', '2', '2'),
       (0, '0', '0', '0'),
       (5, '5', '5', '5');


INSERT INTO PUBLIC.RULE (DESCRIPTION, JSON, NAME, SQL_PART, SQL_STR, TEMPLATE)
VALUES ('Test Description A', 'Test Json A', 'Test Name A', 'Test SqlPart A', 'Test SqlStr A', 'Test Templates A'),
       ('Test Description B', 'Test Json B', 'Test Name B', 'Test SqlPart B', 'Test SqlStr B', 'Test Templates B'),
       ('Test Description C', 'Test Json C', 'Test Name C', 'Test SqlPart C', 'Test SqlStr C', 'Test Templates C'),
       ('Test Description D', 'Test Json D', 'Test Name D', 'Test SqlPart D', 'Test SqlStr D', 'Test Templates D');


INSERT INTO PUBLIC.TRADE (BUY_PRICE, BUY_QUANTITY, SELL_PRICE, SELL_QUANTITY, CREATION_DATE, REVISION_DATE,
                          TRADE_DATE, ACCOUNT, BENCHMARK, BOOK, CREATION_NAME, DEAL_NAME, DEAL_TYPE, REVISION_NAME,
                          SECURITY, SIDE, SOURCE_LIST_ID, STATUS, TRADER, TYPE)
VALUES (null, 1, null, null, null, null, null, 'Test Account A', null, null, null, null, null, null, null, null,
        null, null, null, 'Test Type A'),
       (null, 2, null, null, null, null, null, 'Test Account B', null, null, null, null, null, null, null, null,
        null, null, null, 'Test Type B'),
       (null, 3, null, null, null, null, null, 'Test Account C', null, null, null, null, null, null, null, null,
        null, null, null, 'Test Type C'),
       (null, 4, null, null, null, null, null, 'Test Account D', null, null, null, null, null, null, null, null,
        null, null, null, 'Test Type D');
