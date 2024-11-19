






CREATE TABLE IF NOT EXISTS typesofevents
(
	id 		BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY (MINVALUE 1 START WITH 1 INCREMENT BY 1),
	name 	VARCHAR (50) UNIQUE NOT NULL,
	enabled	BOOLEAN NOT NULL DEFAULT(true),
	PRIMARY KEY(id)
);

INSERT INTO typesofevents(id, name)
SELECT 1, 'Baptism'
WHERE
NOT EXISTS 
(
	SELECT id FROM typesofevents WHERE name = 'Baptism'
);


INSERT INTO typesofevents(id, name)
SELECT 2, 'Birthday'
WHERE
NOT EXISTS 
(
	SELECT id FROM typesofevents WHERE name = 'Birthday'
);


INSERT INTO typesofevents(id, name)
SELECT 3, 'Wedding'
WHERE
NOT EXISTS 
(
	SELECT id FROM typesofevents WHERE name = 'Wedding'
);


INSERT INTO typesofevents(id, name)
SELECT 4, 'Year End Function'
WHERE
NOT EXISTS 
(
	SELECT id FROM typesofevents WHERE name = 'Year End Function'
);



CREATE TABLE IF NOT EXISTS cateringCosts
(
	id 		BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY (MINVALUE 1 START WITH 1 INCREMENT BY 1),
	eventTypeId BIGINT NOT NULL,
	totalCost 	NUMERIC(10,2) UNIQUE NOT NULL,
	comments 	VARCHAR (500) NOT NULL,
	enabled	BOOLEAN NOT NULL DEFAULT(true),
	PRIMARY KEY(id),
	FOREIGN KEY (eventTypeId) REFERENCES typesofevents(id) ON DELETE CASCADE
);

INSERT INTO cateringCosts(id, eventTypeId, totalCost, comments)
SELECT 100, 1, 15500, 'Costs include any selected menu dishes'
WHERE
NOT EXISTS 
(
	SELECT id FROM cateringCosts WHERE eventTypeId = 1
);

INSERT INTO cateringCosts(id, eventTypeId, totalCost, comments)
SELECT 101, 2, 37900, 'Costs include any selected menu dishes'
WHERE
NOT EXISTS 
(
	SELECT id FROM cateringCosts WHERE eventTypeId = 2
);

INSERT INTO cateringCosts(id, eventTypeId, totalCost, comments)
SELECT 102, 3, 65900, 'Costs include any selected menu dishes'
WHERE
NOT EXISTS 
(
	SELECT id FROM cateringCosts WHERE eventTypeId = 3
);

INSERT INTO cateringCosts(id, eventTypeId, totalCost, comments)
SELECT 103, 4, 102600, 'Costs include any selected menu dishes'
WHERE
NOT EXISTS 
(
	SELECT id FROM cateringCosts WHERE eventTypeId = 4
);


CREATE TABLE IF NOT EXISTS phoneTypes
(
	id 		BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY (MINVALUE 1 START WITH 1 INCREMENT BY 1),
	name 	VARCHAR (50) UNIQUE NOT NULL,
	enabled	BOOLEAN NOT NULL DEFAULT(true),
	PRIMARY KEY(id)
);

INSERT INTO phoneTypes(name)
SELECT 'Tel'
WHERE
NOT EXISTS 
(
	SELECT name FROM phoneTypes WHERE name = 'Tel'
);

INSERT INTO phoneTypes(name)
SELECT 'Fax'
WHERE
NOT EXISTS 
(
	SELECT name FROM phoneTypes WHERE name = 'Fax'
);

INSERT INTO phoneTypes(name)
SELECT 'Cell'
WHERE
NOT EXISTS 
(
	SELECT name FROM phoneTypes WHERE name = 'Cell'
);


CREATE TABLE IF NOT EXISTS emailTypes
(
	id 		BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY (MINVALUE 1 START WITH 1 INCREMENT BY 1),
	name 	VARCHAR (50) UNIQUE NOT NULL,
	enabled	BOOLEAN NOT NULL DEFAULT(true),
	PRIMARY KEY(id)
);



INSERT INTO emailTypes(name)
SELECT 'Personal'
WHERE
NOT EXISTS 
(
	SELECT name FROM emailTypes WHERE name = 'Personal'
);


INSERT INTO emailTypes(name)
SELECT 'Work'
WHERE
NOT EXISTS 
(
	SELECT name FROM emailTypes WHERE name = 'Work'
);

INSERT INTO emailTypes(name)
SELECT 'Business'
WHERE
NOT EXISTS 
(
	SELECT name FROM emailTypes WHERE name = 'Business'
);


CREATE TABLE IF NOT EXISTS addressTypes
(
	id 		BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY (MINVALUE 1 START WITH 1 INCREMENT BY 1),
	name 	VARCHAR (50) UNIQUE NOT NULL,
	enabled	BOOLEAN NOT NULL DEFAULT(true),
	PRIMARY KEY(id)
);


INSERT INTO addressTypes(name)
SELECT 'Residential'
WHERE
NOT EXISTS 
(
	SELECT name FROM addressTypes WHERE name = 'Residential'
);

INSERT INTO addressTypes(name)
SELECT 'Business'
WHERE
NOT EXISTS 
(
	SELECT name FROM addressTypes WHERE name = 'Business'
);


CREATE TABLE IF NOT EXISTS orderStages
(
	id 		BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY (MINVALUE 1 START WITH 1 INCREMENT BY 1),
	name 	VARCHAR (50) UNIQUE NOT NULL,
	description 	VARCHAR (500) NULL,
	enabled	BOOLEAN NOT NULL DEFAULT(true),
	PRIMARY KEY(id)
);



INSERT INTO orderStages(id, name, description)
SELECT 1,'SUBMITTED', 'New orders received from the client'
WHERE
NOT EXISTS 
(
	SELECT name FROM orderStages WHERE name = 'SUBMITTED'
);



INSERT INTO orderStages(id, name, description)
SELECT 2, 'AWAITING DEPOSIT', 'Orders received from the client, accepted by the business and awaiting deposit'
WHERE
NOT EXISTS 
(
	SELECT name FROM orderStages WHERE name = 'AWAITING DEPOSIT'
);



INSERT INTO orderStages(id, name, description)
SELECT 3, 'CONFIRMED', 'Orders received from the client, accepted by the business and deposit received'
WHERE
NOT EXISTS 
(
	SELECT name FROM orderStages WHERE name = 'CONFIRMED'
);



INSERT INTO orderStages(id, name, description)
SELECT 4, 'COMPLETED', 'Orders completed by the business'
WHERE
NOT EXISTS 
(
	SELECT name FROM orderStages WHERE name = 'COMPLETED'
);



INSERT INTO orderStages(id, name, description)
SELECT 5, 'FULLY PAID', 'Orders completed by the business and client paid full amount'
WHERE
NOT EXISTS 
(
	SELECT name FROM orderStages WHERE name = 'FULLY PAID'
);
	
	
	
	
	
	


CREATE TABLE IF NOT EXISTS clients (
	id				BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY (MINVALUE 1000000000 START WITH 1000000000 INCREMENT BY 1),
	tittle			VARCHAR (50) NOT NULL,
	name 			VARCHAR (250) NOT NULL,
	surname 		VARCHAR (250) NOT NULL,
	dateOfBirth		DATE NULL,
	gender	 		VARCHAR (50) NOT NULL,
	passcode		VARCHAR (50) NULL,
	securepwd		VARCHAR (500) NULL,
	created_at		TIMESTAMP NOT NULL DEFAULT NOW(),
	updated_at		TIMESTAMP NULL,
	deleted_at		TIMESTAMP NULL,
	PRIMARY KEY(id)
);

INSERT INTO clients(tittle, name, surname, dateOfBirth, gender, passcode, securepwd)
SELECT 'Mr','Vusi', 'Malinga', '1990-04-01', 'Male', 'Pass123', 'Pass123'
WHERE
NOT EXISTS 
(
	SELECT id FROM clients WHERE id = 1000000000
);


CREATE TABLE IF NOT EXISTS users (
	id				BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY (MINVALUE 2000000000 START WITH 2000000000 INCREMENT BY 1),
	name 			VARCHAR (50) NOT NULL,
	surname 		VARCHAR (50) NOT NULL,
	dateOfBirth		DATE NULL,
	username		VARCHAR (50) UNIQUE NULL,
	password		VARCHAR (500) NOT NULL DEFAULT 'Pass123',
	created_at		TIMESTAMP NOT NULL DEFAULT NOW(),
	updated_at		TIMESTAMP NULL,
	deleted_at		TIMESTAMP NULL,
	PRIMARY KEY(id)
);


INSERT INTO users(name, surname, dateOfBirth, username)
SELECT 'Vusi', 'Malinga', '1990-04-01', 'vusieam'
WHERE
NOT EXISTS 
(
	SELECT id FROM users WHERE id = 2000000000
);



CREATE TABLE IF NOT EXISTS phones
(
	id 				BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY (MINVALUE 1 START WITH 1 INCREMENT BY 1),
	clientId	 	BIGINT NOT NULL,
	phoneTypeId 	BIGINT NULL,
	phoneNumber 	VARCHAR (50) UNIQUE NOT NULL,	
	IsPrimary		BOOLEAN NOT NULL,
	enabled			BOOLEAN NOT NULL DEFAULT(true),
	created_at		TIMESTAMP NOT NULL DEFAULT NOW(),
	updated_at		TIMESTAMP NULL,
	deleted_at		TIMESTAMP NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (clientId) REFERENCES clients(id) ON DELETE CASCADE,
	FOREIGN KEY (phoneTypeId) REFERENCES phoneTypes(id) ON DELETE SET NULL
);


INSERT INTO phones(clientId, phoneTypeId, phoneNumber, IsPrimary)
SELECT 1000000000, 3, '0607452041', true
WHERE
NOT EXISTS 
(
	SELECT id FROM phones WHERE clientId = 1000000000
);
	


CREATE TABLE IF NOT EXISTS emails
(
	id 				BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY (MINVALUE 1 START WITH 1 INCREMENT BY 1),
	clientId	 	BIGINT NOT NULL,
	emailTypeId 	BIGINT NULL,
	emailAddress 	VARCHAR (50) UNIQUE NOT NULL,	
	IsPrimary		BOOLEAN NOT NULL,
	enabled			BOOLEAN NOT NULL DEFAULT(true),
	created_at		TIMESTAMP NOT NULL DEFAULT NOW(),
	updated_at		TIMESTAMP NULL,
	deleted_at		TIMESTAMP NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (clientId) REFERENCES clients(id) ON DELETE CASCADE,
	FOREIGN KEY (emailTypeId) REFERENCES emailTypes(id) ON DELETE SET NULL
);

INSERT INTO emails(clientId, emailTypeId, emailAddress, IsPrimary)
SELECT 1000000000, 1, 'vusieam@gmail.com', true
WHERE
NOT EXISTS 
(
	SELECT e.id FROM emails e WHERE e.clientId = 1000000000
);


CREATE TABLE IF NOT EXISTS addresses
(
	id 				BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY (MINVALUE 1 START WITH 1 INCREMENT BY 1),
	clientId	 	BIGINT NOT NULL,
	addressTypeId 	BIGINT NULL,
	streetNumber 	VARCHAR (50) NULL,	
	streetName	 	VARCHAR (250) NULL,	
	complexBuilding	 	VARCHAR (250) NULL,	
	surburb		 	VARCHAR (250) NULL,	
	city		 	VARCHAR (250) NULL,	
	zipcode		 	VARCHAR (250) NULL,	
	province	 	VARCHAR (250) NULL,	
	country		 	VARCHAR (250) NULL,	
	IsPrimary		BOOLEAN NOT NULL,
	enabled			BOOLEAN NOT NULL DEFAULT(true),
	created_at		TIMESTAMP NOT NULL DEFAULT NOW(),
	updated_at		TIMESTAMP NULL,
	deleted_at		TIMESTAMP NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (clientId) REFERENCES clients(id) ON DELETE CASCADE,
	FOREIGN KEY (addressTypeId) REFERENCES addressTypes(id) ON DELETE SET NULL
);






CREATE TABLE IF NOT EXISTS bookingDetails
(
	id 				BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY (MINVALUE 9000000 START WITH 9000000 INCREMENT BY 1),
	clientId	 	BIGINT NOT NULL,
	typeofEventId 	BIGINT NOT NULL,
	eventDate	 	DATE NOT NULL,	
	eventTime	 	TIME NOT NULL,	
	expectedAdultsAttendance	 	integer DEFAULT 0,	
	expectedKidsAttendance	 		integer DEFAULT 0,
	emailAddress	VARCHAR (500) NULL,		
	cellMobile	VARCHAR (500) NULL,		
	telNo	VARCHAR (500) NULL,	
	
	adultMenuTacos	BOOLEAN NOT NULL DEFAULT(false),	
	adultMenuChickenWrap	BOOLEAN NOT NULL DEFAULT(false),	
	adultMenuChickenKebab	BOOLEAN NOT NULL DEFAULT(false),	
	
	kidsMenuMiniPizzaCheese	BOOLEAN NOT NULL DEFAULT(false),	
	kidsMenuMiniMiniPizza	BOOLEAN NOT NULL DEFAULT(false),	
	kidsMenuMiniSliders	BOOLEAN NOT NULL DEFAULT(false),	
	kidsMenuMiniHandpie	BOOLEAN NOT NULL DEFAULT(false),	
	
	menuDrinksIcetea	BOOLEAN NOT NULL DEFAULT(false),	
	menuDrinksOrangeJuice	BOOLEAN NOT NULL DEFAULT(false),	
	menuDrinksAppleJuice	BOOLEAN NOT NULL DEFAULT(false),	
	menuDrinksFantaOrange	BOOLEAN NOT NULL DEFAULT(false),	
	menuDrinksCocacola	BOOLEAN NOT NULL DEFAULT(false),	
	menuDrinksApricotJuice	BOOLEAN NOT NULL DEFAULT(false),	
	
	menuDessertOreoPudding	BOOLEAN NOT NULL DEFAULT(false),	
	menuDessertOreoBalls	BOOLEAN NOT NULL DEFAULT(false),	
	menuDessertChurros	BOOLEAN NOT NULL DEFAULT(false),	
	menuDessertDonuts	BOOLEAN NOT NULL DEFAULT(false),	
	menuDessertMalva	BOOLEAN NOT NULL DEFAULT(false),	
	menuDessertBerry	BOOLEAN NOT NULL DEFAULT(false),
			
	decoration		BOOLEAN NOT NULL DEFAULT(false),	
	themeDetails	VARCHAR (4000) NULL,	
	quotationAmount		NUMERIC(10,2) NULL,	
	discountpercentage		NUMERIC(5,2) NULL,
	finalQuotationAmount		NUMERIC(10,4) NULL,	
	
	stageId			BIGINT NOT NULL DEFAULT(1),--SUBMITTED|ACCEPTED|PAID|COMPLETED
	created_at		TIMESTAMP NOT NULL DEFAULT NOW(),
	updated_at		TIMESTAMP NULL,
	deleted_at		TIMESTAMP NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (clientId) REFERENCES clients(id) ON DELETE CASCADE,
	FOREIGN KEY (typeofEventId) REFERENCES typesofevents(id) ON DELETE CASCADE,
	FOREIGN KEY (stageId) REFERENCES orderStages(id) ON DELETE CASCADE
);



CREATE OR REPLACE FUNCTION calculate_booking_cost() 
RETURNS trigger AS $calculate_booking_cost$
BEGIN
    UPDATE bookingDetails 
	SET quotationAmount =
		(
			SELECT cs.totalCost 
			FROM public.cateringCosts cs
			WHERE cs.eventTypeId = NEW.typeofEventId
		),
		discountpercentage = 
		(
			SELECT
				CASE 
					WHEN
					(
						(NEW.expectedAdultsAttendance + NEW.expectedKidsAttendance)  >= 40
					) THEN 15.0
					ELSE 0
				END AS DiscountOffer

		),
		finalQuotationAmount = 
		(
			SELECT
				CASE 
					WHEN
					(
						(NEW.expectedAdultsAttendance + NEW.expectedKidsAttendance) >= 40
					) THEN 
					(
						SELECT ROUND(((SELECT cs.totalCost FROM public.cateringCosts cs WHERE cs.eventTypeId = NEW.typeofEventId) * 0.15), 2)
					)
					ELSE (SELECT cs.totalCost FROM public.cateringCosts cs WHERE cs.eventTypeId = NEW.typeofEventId)
				END AS finalCost
			
	 	)
	 WHERE id = NEW.id and eventDate = NEW.eventDate;

    RETURN NEW;
END;
$calculate_booking_cost$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER booking_transaction_added
  AFTER INSERT
  ON bookingDetails
  FOR EACH ROW
  EXECUTE PROCEDURE calculate_booking_cost();





CREATE TABLE IF NOT EXISTS bookingDetailsAddresses
(
	id 				BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY (MINVALUE 1 START WITH 1 INCREMENT BY 1),
	orderId		 	BIGINT NOT NULL,
	addressTypeId 	BIGINT NULL,
	streetNumber 	VARCHAR (50) NULL,	
	streetName	 	VARCHAR (250) NULL,	
	complexBuilding	 	VARCHAR (250) NULL,	
	surburb		 	VARCHAR (250) NULL,	
	city		 	VARCHAR (250) NULL,	
	zipcode		 	VARCHAR (250) NULL,	
	province	 	VARCHAR (250) NULL,	
	country		 	VARCHAR (250) NULL,
	created_at		TIMESTAMP NOT NULL DEFAULT NOW(),
	updated_at		TIMESTAMP NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (orderId) REFERENCES bookingDetails(id) ON DELETE CASCADE,
	FOREIGN KEY (addressTypeId) REFERENCES addressTypes(id) ON DELETE SET NULL
);


CREATE TABLE IF NOT EXISTS bookingDetailsTransactions
(
	id 				BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY (MINVALUE 1200000000 START WITH 1200000000 INCREMENT BY 2),
	orderId		 	BIGINT NOT NULL,
	credit			NUMERIC(10,2) NOT NULL,
	transactionComment varchar default '',
	runningBalance	NUMERIC(10,2) NULL,
	transactionDate		DATE NOT NULL DEFAULT NOW(),
	created_at		DATE NOT NULL DEFAULT NOW(),
	PRIMARY KEY(id),
	FOREIGN KEY (orderId) REFERENCES bookingDetails(id) ON DELETE CASCADE
);

CREATE OR REPLACE FUNCTION calculate_running_balance() 
RETURNS trigger AS $calculate_running_balance$
BEGIN
    UPDATE bookingDetailsTransactions 
	SET runningBalance = 
	(
		SELECT SUM(f.credit)
		 FROM bookingDetailsTransactions f
		 WHERE f.orderId = NEW.orderId
	 )
	 WHERE orderId = NEW.orderId and transactionDate = NEW.transactionDate;

    RETURN NEW;
END;
$calculate_running_balance$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER booking_transaction_added
  AFTER INSERT
  ON bookingDetailsTransactions
  FOR EACH ROW
  EXECUTE PROCEDURE calculate_running_balance();






-------PROCEDURES


CREATE OR REPLACE PROCEDURE public.md_captureBookingPayments(
	IN bookingId BIGINT,
	IN paymentDate Date,
	IN paymentAmount NUMERIC(10,2),
	IN clientComments VARCHAR,
	INOUT responsestatus VARCHAR,
	INOUT responsecode VARCHAR,
	INOUT responsemessage VARCHAR)
LANGUAGE 'plpgsql'
AS $BODY$
BEGIN
	INSERT INTO public.bookingDetailsTransactions(orderid, credit, transactiondate, transactioncomment)
	VALUES(bookingId, paymentAmount, paymentDate, clientComments);
	responsestatus := 'true';
	responsestatus := '200';
	responsestatus := 'Successful';
	COMMIT;
END;
$BODY$;




CREATE OR REPLACE FUNCTION public.func_clientAuth
(
	loginName VARCHAR(50),
	loginPassword	VARCHAR(50)
)
RETURNS TABLE(id BIGINT, name VARCHAR, surname VARCHAR, dateOfBirth DATE) 
AS $$
BEGIN
    RETURN QUERY 
    SELECT c.id, c.name, c.surname, c.dateOfBirth 
	FROM "public".clients c
	inner join "public".phones p on p.clientid = c.id
	inner join "public".emails e on e.clientid = c.id
	WHERE 
	(
		p.phonenumber = loginName 
		OR 
		e.emailAddress = loginName
	) 
	and
	(
		c.securepwd = loginPassword
	);
END;
$$ LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION public.func_adminauth(
	loginName varchar(50) ,
	loginPassword varchar(50)
)
RETURNS TABLE(id BIGINT, name VARCHAR, surname VARCHAR, dateOfBirth DATE)
LANGUAGE 'plpgsql'
AS $BODY$
BEGIN	
	RETURN QUERY
		SELECT u.id, u.name, u.surname, u.dateofbirth
		FROM public.users u 
		WHERE 
		(
			u.username = loginName AND u.password = loginPassword
		);
END;
$BODY$;



-- PROCEDURE: public.md_createaccount(character varying, character varying, timestamp without time zone, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying)

-- DROP PROCEDURE IF EXISTS public.md_createaccount(character varying, character varying, timestamp without time zone, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying);

CREATE OR REPLACE PROCEDURE public.md_createaccount(
	IN fname character varying,
	IN fsurname character varying,
	IN fdateofbirth timestamp without time zone,
	IN fpasscode character varying,
	IN fsecuredpwd character varying,
	IN femail character varying,
	IN fcell character varying,
	IN fphone character varying,
	INOUT fclientid character varying,
	INOUT responsestatus character varying,
	INOUT responsecode character varying,
	INOUT responsemessage character varying)
LANGUAGE 'plpgsql'
AS $BODY$
DECLARE
		pfclientId BIGINT;
		--presponseStatus BOOLEAN;
		--presponseCode BIGINT;
		--responseMessage VARCHAR (500);
BEGIN
    SELECT p.clientId 
	FROM phones p
	LEFT JOIN emails e on e.clientid = p.clientid
	INTO pfclientId 
	WHERE (p.phoneNumber = fcell OR p.phoneNumber = fphone OR e.emailaddress = femail);

	IF pfclientId IS NULL OR pfclientId < 1 THEN
	
		INSERT INTO clients("name", surname, dateofbirth, passcode, securepwd)
		VALUES(fname, fsurname, fdateOfBirth, fpasscode, fsecuredpwd)
		RETURNING id INTO pfclientId;

		RAISE NOTICE 'Client Id % has been added', 
		pfclientId; 

		IF fcell IS NOT NULL THEN
			INSERT INTO phones(clientid, phonetypeid, phonenumber, isprimary)
			SELECT pfclientId, 1, fcell, true
			WHERE
			NOT EXISTS 
			(
				SELECT id FROM phones p WHERE p.clientId = pfclientId AND p.phonetypeid = 1
			);
		END IF;

		IF fphone IS NOT NULL AND fphone <> '' THEN
			INSERT INTO phones(clientid, phonetypeid, phonenumber, isprimary)
			SELECT pfclientId, 2, fphone, false
			WHERE
			NOT EXISTS 
			(
				SELECT id FROM phones p WHERE p.clientId = pfclientId AND p.phonetypeid = 2
			);
		END IF;

		fclientId := pfclientId;
		responseStatus := true;
		responseCode := 200;		
		responseMessage := 'Successfully created account';
		RAISE NOTICE 'Successfully created account with Id %', 
		pfclientId; 
		
	ELSE
	
		fclientId := pfclientId;
		responseStatus := false;
		responseCode := 409;
		responseMessage := 'Account with any of the provided phones or email already exists';
		RAISE NOTICE 'Account any of the provided phones (cell % or phone %) already exists with Id %', 
		fcell, 
		fphone,
		pfclientId;
		
	END IF;
    COMMIT;
END;
$BODY$;
ALTER PROCEDURE public.md_createaccount(character varying, character varying, timestamp without time zone, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying)
    OWNER TO postgres;




CREATE OR REPLACE PROCEDURE public.md_createbooking(
	IN fclientId BIGINT,
	IN ftypeofEventId BIGINT,
	IN feventDate timestamp without time zone,
	IN feventTime time,
	IN fexpectedAdultsAttendance INTEGER DEFAULT 0,
	IN fexpectedKidsAttendance INTEGER DEFAULT 0,
	IN femailAddress character DEFAULT '',
	IN fcellMobile character DEFAULT '',
	IN ftelNo character DEFAULT '',
	
	IN fadultMenuTacos BOOLEAN DEFAULT(false),
	IN fadultMenuChickenWrap BOOLEAN DEFAULT(false),
	IN fadultMenuChickenKebab BOOLEAN DEFAULT(false),
	
	IN fkidsMenuMiniPizzaCheese BOOLEAN DEFAULT(false),
	IN fkidsMenuMiniMiniPizza BOOLEAN DEFAULT(false),
	IN fkidsMenuMiniSliders BOOLEAN DEFAULT(false),
	IN fkidsMenuMiniHandpie BOOLEAN DEFAULT(false),
	
	IN fmenuDrinksIcetea BOOLEAN DEFAULT(false),
	IN fmenuDrinksOrangeJuice BOOLEAN DEFAULT(false),
	IN fmenuDrinksAppleJuice BOOLEAN DEFAULT(false),
	IN fmenuDrinksFantaOrange BOOLEAN DEFAULT(false),
	IN fmenuDrinksCocacola BOOLEAN DEFAULT(false),
	IN fmenuDrinksApricotJuice BOOLEAN DEFAULT(false),
	
	IN fmenuDessertOreoPudding BOOLEAN DEFAULT(false),
	IN fmenuDessertOreoBalls BOOLEAN DEFAULT(false),
	IN fmenuDessertChurros BOOLEAN DEFAULT(false),
	IN fmenuDessertDonuts BOOLEAN DEFAULT(false),
	IN fmenuDessertMalva BOOLEAN DEFAULT(false),
	IN fmenuDessertBerry BOOLEAN DEFAULT(false),
	
	IN fdecoration BOOLEAN DEFAULT(false),	
	IN fthemeDetails character DEFAULT '',
	
	IN faddressTypeId BIGINT DEFAULT(0),
	IN fstreetNumber character DEFAULT '',
	IN fstreetName character DEFAULT '',
	IN fcomplexBuilding character DEFAULT '',
	IN fsurburb character DEFAULT '',
	IN fcity character DEFAULT '',
	IN fzipcode character DEFAULT '',
	IN fprovince character DEFAULT '',
	IN fcountry character DEFAULT '',
	
	INOUT referenceNo VARCHAR(50) = '',
	INOUT responsestatus VARCHAR(50) = '',
	INOUT responsecode VARCHAR(50) = '',
	INOUT responsemessage VARCHAR(5000) = ''
)
LANGUAGE 'plpgsql'
AS $BODY$
DECLARE
		pOrderId BIGINT;
BEGIN

	SELECT p.id 
	FROM bookingDetails p	
	INTO pOrderId 
	WHERE (p.eventDate = feventDate);

	IF pOrderId IS NULL OR pOrderId < 1 THEN

		INSERT INTO bookingDetails(clientId, typeofEventId, eventDate, eventTime, expectedAdultsAttendance, expectedKidsAttendance, emailAddress, cellMobile,
		telNo, adultMenuTacos, adultMenuChickenWrap, adultMenuChickenKebab, kidsMenuMiniPizzaCheese, kidsMenuMiniMiniPizza, kidsMenuMiniSliders, kidsMenuMiniHandpie, 
		menuDrinksIcetea, menuDrinksOrangeJuice, menuDrinksAppleJuice, menuDrinksFantaOrange, menuDrinksCocacola, menuDrinksApricotJuice,
		menuDessertOreoPudding, menuDessertOreoBalls, menuDessertChurros, menuDessertDonuts, menuDessertMalva, menuDessertBerry, decoration, themeDetails)
		VALUES(fclientId, ftypeofEventId, feventDate, feventTime, fexpectedAdultsAttendance, fexpectedKidsAttendance, femailAddress, fcellMobile,
		ftelNo, fadultMenuTacos, fadultMenuChickenWrap, fadultMenuChickenKebab, fkidsMenuMiniPizzaCheese, fkidsMenuMiniMiniPizza, fkidsMenuMiniSliders, fkidsMenuMiniHandpie, 
		fmenuDrinksIcetea, fmenuDrinksOrangeJuice, fmenuDrinksAppleJuice, fmenuDrinksFantaOrange, fmenuDrinksCocacola, fmenuDrinksApricotJuice,
		fmenuDessertOreoPudding, fmenuDessertOreoBalls, fmenuDessertChurros, fmenuDessertDonuts, fmenuDessertMalva, fmenuDessertBerry, fdecoration, fthemeDetails)
			RETURNING id INTO pOrderId;


		IF pOrderId IS NOT NULL AND pOrderId > 1 THEN
			IF faddressTypeId > 0 THEN
				INSERT INTO bookingDetailsAddresses(orderId, addressTypeId, streetNumber, streetName, complexBuilding, surburb, city, zipcode, province, country)
				VALUES(pOrderId, faddressTypeId, fstreetNumber, fstreetName, fcomplexBuilding, fsurburb, fcity, fzipcode, fprovince, fcountry);
			END IF;
		END IF;

		IF pOrderId IS NOT NULL AND pOrderId > 1 THEN
			referenceNo := CAST(pOrderId AS VARCHAR(50));
			responseStatus := true;
			responseCode := 200;		
			responseMessage := 'Successfully created booking';
			RAISE NOTICE 'Successfully created booking with Id %', 
			pOrderId; 
			
		ELSE
			referenceNo := '';
			responseStatus := false;
			responseCode := 404;		
			responseMessage := 'Failed to create booking';
			RAISE NOTICE 'Failed to create booking with Id %', 
			pOrderId; 
			
		END IF;    

	ELSE
		referenceNo := CAST(pOrderId AS VARCHAR(50));
		responseStatus := false;
		responseCode := 409;		
		responseMessage := 'There is already an event booked on the selected date. Please choose another date.';
		RAISE NOTICE 'There is already an event booked on the selected date. Please choose another date with Id %', 
		pOrderId; 
	END IF;
    COMMIT;
END;
$BODY$;




CREATE OR REPLACE FUNCTION public.md_getbookingsbyclient
(
	IN clientIdentifier BIGINT
) 
RETURNS TABLE
(
	clientid BIGINT, name VARCHAR, surname VARCHAR, gender VARCHAR, dateOfBirth DATE,
	orderId BIGINT, typeofEventId BIGINT, eventDate DATE, eventTime TIME, expectedAdultsAttendance INTEGER, 
	expectedKidsAttendance INTEGER, emailAddress VARCHAR, cellMobile VARCHAR, telNo VARCHAR, adultMenuTacos BOOLEAN, 
	adultMenuChickenWrap BOOLEAN, adultMenuChickenKebab BOOLEAN, kidsMenuMiniPizzaCheese BOOLEAN, kidsMenuMiniMiniPizza BOOLEAN, 
	kidsMenuMiniSliders BOOLEAN, kidsMenuMiniHandpie BOOLEAN, menuDrinksIcetea BOOLEAN, menuDrinksOrangeJuice BOOLEAN, 
	menuDrinksAppleJuice BOOLEAN, menuDrinksFantaOrange BOOLEAN, 
	menuDrinksCocacola BOOLEAN, menuDrinksApricotJuice BOOLEAN, menuDessertOreoPudding BOOLEAN, menuDessertOreoBalls BOOLEAN, menuDessertChurros BOOLEAN, 
	menuDessertDonuts BOOLEAN, menuDessertMalva BOOLEAN, menuDessertBerry BOOLEAN, decoration BOOLEAN, themeDetails VARCHAR, 
	addressTypeId BIGINT, streetNumber VARCHAR, streetName VARCHAR, complexBuilding VARCHAR, surburb VARCHAR, city VARCHAR, 
	zipcode VARCHAR, province VARCHAR, country VARCHAR, quoteAmount NUMERIC(10,2), discountpercent NUMERIC(5,2), finalQuoteAmount NUMERIC(10,2), currentBalance NUMERIC(10,2)		
)
LANGUAGE 'plpgsql'
AS $BODY$ 
BEGIN
	RETURN QUERY 
		WITH RECURSIVE cte_payments AS (
    		SELECT bf.orderid, 	SUM(credit) AS runningBalance
    		FROM public.bookingDetailsTransactions bf
			INNER JOIN public.bookingDetails bd on bd.id = bf.orderid
    		WHERE bd.clientid = clientIdentifier
			GROUP BY bf.orderid
		)
		SELECT d.clientid, cl.name, cl.surname, cl.gender, cl.dateOfBirth, 
		d.id as orderId, d.typeofEventId, d.eventDate, d.eventTime, d.expectedAdultsAttendance, d.expectedKidsAttendance, 
		d.emailAddress, d.cellMobile, d.telNo, 
		d.adultMenuTacos, d.adultMenuChickenWrap, d.adultMenuChickenKebab, d.kidsMenuMiniPizzaCheese, d.kidsMenuMiniMiniPizza, d.kidsMenuMiniSliders, 
		d.kidsMenuMiniHandpie, 
		d.menuDrinksIcetea, d.menuDrinksOrangeJuice, d.menuDrinksAppleJuice, d.menuDrinksFantaOrange, d.menuDrinksCocacola, d.menuDrinksApricotJuice,
		d.menuDessertOreoPudding, d.menuDessertOreoBalls, d.menuDessertChurros, d.menuDessertDonuts, d.menuDessertMalva, d.menuDessertBerry, 
		d.decoration, d.themeDetails, 
		a.addressTypeId, a.streetNumber, a.streetName, a.complexBuilding, a.surburb, a.city, a.zipcode,a.province, a.country,
		d.quotationAmount, d.discountpercentage, finalQuotationAmount, cp.runningBalance
		FROM public.bookingDetails d
		INNER JOIN public.clients cl on cl.id = d.clientid
		INNER JOIN public.bookingDetailsAddresses a on a.orderid = d.id
		LEFT JOIN cte_payments cp on cp.orderid = d.id
		
		 WHERE 
		 (
		 	d.clientid = clientIdentifier
		 );
END;
$BODY$;



CREATE OR REPLACE FUNCTION public.md_getbookingbyId
(
	IN bookingId BIGINT
) RETURNS TABLE
(
	clientid BIGINT, name VARCHAR, surname VARCHAR, gender VARCHAR, dateOfBirth DATE,
	orderId BIGINT, typeofEventId BIGINT, eventDate DATE, eventTime TIME, expectedAdultsAttendance INTEGER, 
	expectedKidsAttendance INTEGER, emailAddress VARCHAR, cellMobile VARCHAR, telNo VARCHAR, adultMenuTacos BOOLEAN, 
	adultMenuChickenWrap BOOLEAN, adultMenuChickenKebab BOOLEAN, kidsMenuMiniPizzaCheese BOOLEAN, kidsMenuMiniMiniPizza BOOLEAN, 
	kidsMenuMiniSliders BOOLEAN, kidsMenuMiniHandpie BOOLEAN, menuDrinksIcetea BOOLEAN, menuDrinksOrangeJuice BOOLEAN, 
	menuDrinksAppleJuice BOOLEAN, menuDrinksFantaOrange BOOLEAN, 
	menuDrinksCocacola BOOLEAN, menuDrinksApricotJuice BOOLEAN, menuDessertOreoPudding BOOLEAN, menuDessertOreoBalls BOOLEAN, menuDessertChurros BOOLEAN, 
	menuDessertDonuts BOOLEAN, menuDessertMalva BOOLEAN, menuDessertBerry BOOLEAN, decoration BOOLEAN, themeDetails VARCHAR, 
	addressTypeId BIGINT, streetNumber VARCHAR, streetName VARCHAR, complexBuilding VARCHAR, surburb VARCHAR, city VARCHAR, 
	zipcode VARCHAR, province VARCHAR, country VARCHAR, quoteAmount NUMERIC(10,2), discountpercent NUMERIC(5,2), finalQuoteAmount NUMERIC(10,2), currentBalance NUMERIC(10,2)		
)
LANGUAGE 'plpgsql'
AS $BODY$ 
BEGIN
	RETURN QUERY 
		WITH RECURSIVE cte_payments AS (
    		SELECT bf.orderid, 	SUM(credit) AS runningBalance
    		FROM public.bookingDetailsTransactions bf
    		WHERE bf.orderid = bookingId
			GROUP BY bf.orderid
		)
		SELECT d.clientid, cl.name, cl.surname, cl.gender, cl.dateOfBirth, 
		d.id as orderId, d.typeofEventId, d.eventDate, d.eventTime, d.expectedAdultsAttendance, d.expectedKidsAttendance, 
		d.emailAddress, d.cellMobile, d.telNo, 
		d.adultMenuTacos, d.adultMenuChickenWrap, d.adultMenuChickenKebab, d.kidsMenuMiniPizzaCheese, d.kidsMenuMiniMiniPizza, d.kidsMenuMiniSliders, 
		d.kidsMenuMiniHandpie, 
		d.menuDrinksIcetea, d.menuDrinksOrangeJuice, d.menuDrinksAppleJuice, d.menuDrinksFantaOrange, d.menuDrinksCocacola, d.menuDrinksApricotJuice,
		d.menuDessertOreoPudding, d.menuDessertOreoBalls, d.menuDessertChurros, d.menuDessertDonuts, d.menuDessertMalva, d.menuDessertBerry, 
		d.decoration, d.themeDetails, 
		a.addressTypeId, a.streetNumber, a.streetName, a.complexBuilding, a.surburb, a.city, a.zipcode,a.province, a.country,
		d.quotationAmount, d.discountpercentage, finalQuotationAmount, cp.runningBalance  
		FROM public.bookingDetails d
		INNER JOIN public.clients cl on cl.id = d.clientid
		INNER JOIN public.bookingDetailsAddresses a on a.orderid = d.id
		LEFT JOIN cte_payments cp on cp.orderid = d.id
		 WHERE 
		 (
		 	d.id = bookingId
		 );
END;
$BODY$;



CREATE OR REPLACE FUNCTION public.md_getbookingbyStage
(
	IN statusId BIGINT
) RETURNS TABLE
(
	clientid BIGINT, name VARCHAR, surname VARCHAR, gender VARCHAR, dateOfBirth DATE,
	orderId BIGINT, typeofEventId BIGINT, eventDate DATE, eventTime TIME, expectedAdultsAttendance INTEGER, 
	expectedKidsAttendance INTEGER, emailAddress VARCHAR, cellMobile VARCHAR, telNo VARCHAR, adultMenuTacos BOOLEAN, 
	adultMenuChickenWrap BOOLEAN, adultMenuChickenKebab BOOLEAN, kidsMenuMiniPizzaCheese BOOLEAN, kidsMenuMiniMiniPizza BOOLEAN, 
	kidsMenuMiniSliders BOOLEAN, kidsMenuMiniHandpie BOOLEAN, menuDrinksIcetea BOOLEAN, menuDrinksOrangeJuice BOOLEAN, 
	menuDrinksAppleJuice BOOLEAN, menuDrinksFantaOrange BOOLEAN, 
	menuDrinksCocacola BOOLEAN, menuDrinksApricotJuice BOOLEAN, menuDessertOreoPudding BOOLEAN, menuDessertOreoBalls BOOLEAN, menuDessertChurros BOOLEAN, 
	menuDessertDonuts BOOLEAN, menuDessertMalva BOOLEAN, menuDessertBerry BOOLEAN, decoration BOOLEAN, themeDetails VARCHAR, 
	addressTypeId BIGINT, streetNumber VARCHAR, streetName VARCHAR, complexBuilding VARCHAR, surburb VARCHAR, city VARCHAR, 
	zipcode VARCHAR, province VARCHAR, country VARCHAR, quoteAmount NUMERIC(10,2), discountpercent NUMERIC(5,2), finalQuoteAmount NUMERIC(10,2), currentBalance NUMERIC(10,2)		
)
LANGUAGE 'plpgsql'
AS $BODY$ 
BEGIN
	RETURN QUERY 
		WITH RECURSIVE cte_payments AS (
    		SELECT bf.orderid, 	SUM(credit) AS runningBalance
    		FROM public.bookingDetailsTransactions bf
			INNER JOIN public.bookingDetails od ON od.id = bf.orderid 
    		WHERE od.stageId = statusId
			GROUP BY bf.orderid
		)
		SELECT d.clientid, cl.name, cl.surname, cl.gender, cl.dateOfBirth, 
		d.id as orderId, d.typeofEventId, d.eventDate, d.eventTime, d.expectedAdultsAttendance, d.expectedKidsAttendance, 
		d.emailAddress, d.cellMobile, d.telNo, 
		d.adultMenuTacos, d.adultMenuChickenWrap, d.adultMenuChickenKebab, d.kidsMenuMiniPizzaCheese, d.kidsMenuMiniMiniPizza, d.kidsMenuMiniSliders, 
		d.kidsMenuMiniHandpie, 
		d.menuDrinksIcetea, d.menuDrinksOrangeJuice, d.menuDrinksAppleJuice, d.menuDrinksFantaOrange, d.menuDrinksCocacola, d.menuDrinksApricotJuice,
		d.menuDessertOreoPudding, d.menuDessertOreoBalls, d.menuDessertChurros, d.menuDessertDonuts, d.menuDessertMalva, d.menuDessertBerry, 
		d.decoration, d.themeDetails, 
		a.addressTypeId, a.streetNumber, a.streetName, a.complexBuilding, a.surburb, a.city, a.zipcode,a.province, a.country,
		d.quotationAmount, d.discountpercentage, finalQuotationAmount, cp.runningBalance  
		FROM public.bookingDetails d
		INNER JOIN public.clients cl on cl.id = d.clientid
		INNER JOIN public.bookingDetailsAddresses a on a.orderid = d.id
		INNER JOIN public.orderStages ost ON ost.id = d.stageId
		LEFT JOIN cte_payments cp on cp.orderid = d.id
		 WHERE 
		 (
		 	ost.id = statusId
		 );
END;
$BODY$;