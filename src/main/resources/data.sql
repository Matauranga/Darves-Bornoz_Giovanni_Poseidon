INSERT INTO PUBLIC.USERS(ID, FULLNAME, USERNAME, PASSWORD, ROLE)
VALUES (-1, 'Administrator', 'admin', '$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa', 'ADMIN'),
       (-2, 'User', 'user', '$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa', 'USER'),
       (-3, 'GioAdmin', 'g', '$2a$10$no1Bnc0L/Jq5WnNtRA8pOu85KJmSfgZCavocgseLLKaExr4ccXNZy', 'ADMIN'),
       (-4, 'GioUser', 'a', '$2a$10$i0PKMsYCLqV1nldoI6uM3ut8I9NIisPMJUHcfr6RPYYfyfVoEvWE2', 'USER');


INSERT INTO PUBLIC.BIDLIST (ASK, ASK_QUANTITY, BID, BID_LIST_ID, BID_QUANTITY, BID_LIST_DATE, CREATION_DATE,
                            REVISION_DATE, ACCOUNT, BENCHMARK, BOOK, COMMENTARY, CREATION_NAME, DEAL_NAME, DEAL_TYPE,
                            REVISION_NAME, SECURITY, SIDE, SOURCE_LIST_ID, STATUS, TRADER, TYPE)
VALUES (null, null, null, -1, 1, null, null, null, 'Test Account A', null, null, null, null, null, null, null, null,
        null, null, null, null, 'Test Type A'),
       (null, null, null, -2, 2, null, null, null, 'Test Account B', null, null, null, null, null, null, null, null,
        null, null, null, null, 'Test Type B');


INSERT INTO PUBLIC.CURVEPOINT (CURVE_ID, ID, TERM, "value", AS_OF_DATE, CREATION_DATE)
VALUES (null, -1, 10, 1, null, null),
       (null, -2, 20, 2, null, null);


INSERT INTO PUBLIC.RATING (ID, ORDER_NUMBER, FITCH_RATING, MOODYS_RATING, SANDPRATING)
VALUES (-1, 1, '1', '1', '1'),
       (-2, 2, '2', '2', '2');


INSERT INTO PUBLIC.RULENAME (ID, DESCRIPTION, JSON, NAME, SQL_PART, SQL_STR, TEMPLATE)
VALUES (-1, 'Test Description A', 'Test Json A', 'Test Name A', 'Test SqlPart A', 'Test SqlStr A', 'Test Templates A'),
       (-2, 'Test Description B', 'Test Json B', 'Test Name B', 'Test SqlPart B', 'Test SqlStr B', 'Test Templates B');


INSERT INTO PUBLIC.TRADE (BUY_PRICE, BUY_QUANTITY, SELL_PRICE, SELL_QUANTITY, TRADE_ID, CREATION_DATE, REVISION_DATE,
                          TRADE_DATE, ACCOUNT, BENCHMARK, BOOK, CREATION_NAME, DEAL_NAME, DEAL_TYPE, REVISION_NAME,
                          SECURITY, SIDE, SOURCE_LIST_ID, STATUS, TRADER, TYPE)
VALUES (null, 1, null, null, -1, null, null, null, 'Test Account A', null, null, null, null, null, null, null, null,
        null, null, null, 'Test Type A'),
       (null, 2, null, null, -2, null, null, null, 'Test Account B', null, null, null, null, null, null, null, null,
        null, null, null, 'Test Type B');
