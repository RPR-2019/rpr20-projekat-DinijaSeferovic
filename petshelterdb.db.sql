BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "admins" (
	"id"	INTEGER NOT NULL,
	"username"	TEXT NOT NULL,
	"passwd"	TEXT NOT NULL,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "users" (
	"id"	INTEGER NOT NULL,
	"name"	TEXT,
	"username"	TEXT NOT NULL,
	"email"	TEXT NOT NULL,
	"passwd"	TEXT NOT NULL,
	"wkhours"	NUMERIC,
	"roomates"	INTEGER,
	"homewk"	INTEGER,
	"yard"	INTEGER,
	"pets"	INTEGER,
	"appointment"	TEXT,
	"approved"	INTEGER,
	"profile"	INTEGER,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "dogs" (
	"id"	INTEGER NOT NULL,
	"name"	TEXT,
	"sex"	TEXT,
	"age"	TEXT,
	"breed"	TEXT,
	"imgsrc"	TEXT,
	"dedicationhours"	REAL,
	"peopletolerance"	INTEGER,
	"yardneed"	INTEGER,
	"pettolerance"	INTEGER,
	"adopted"	INTEGER,
	"urgent"	INTEGER,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "cats" (
	"id"	INTEGER NOT NULL,
	"name"	TEXT,
	"sex"	TEXT,
	"age"	TEXT,
	"breed"	TEXT,
	"imgsrc"	TEXT,
	"dedicationhours"	REAL,
	"peopletolerance"	INTEGER,
	"yardneed"	INTEGER,
	"pettolerance"	INTEGER,
	"adopted"	INTEGER,
	"urgent"	INTEGER,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "likedpets" (
	"id"	INTEGER NOT NULL,
	"catid"	INTEGER,
	"dogid"	INTEGER,
	"userid"	INTEGER,
	FOREIGN KEY("dogid") REFERENCES "dogs"("id"),
	FOREIGN KEY("userid") REFERENCES "users"("id"),
	FOREIGN KEY("catid") REFERENCES "cats"("id"),
	PRIMARY KEY("id")
);
INSERT or IGNORE INTO "admins" VALUES (1,'admin','admin');
INSERT or IGNORE INTO "users" VALUES (1,'Dinija Seferovic','dseferovic','dseferovic@gmail.com','ds',0,0,1,0,1,'2021-10-10 ',NULL, 1);
INSERT or IGNORE INTO "users" VALUES (2,'Miho Mihic','miho123','miho@gmail.com','mihom',0,4,0,0,0,'2021-09-24',NULL,1);
INSERT or IGNORE INTO "users" VALUES (3,'Maja Majic','majamaj','name@gmail.com','maja',8.1,3,0,0,1,NULL,NULL,1);
INSERT or IGNORE INTO "dogs" VALUES (1,'Basi','M','1 year','Basenji','/img/dogbreeds/basenji.jpg',6.0,1,0,1,0,1);
INSERT or IGNORE INTO "dogs" VALUES (2,'Pea','F','4 years','Beagle','/img/dogbreeds/beagle.jpg',8.0,1,1,1,0,NULL);
INSERT or IGNORE INTO "dogs" VALUES (3,'Jack','M','5 months','Boerboel','/img/dogbreeds/boxer.jpg',9.0,1,1,1,0,NULL);
INSERT or IGNORE INTO "dogs" VALUES (4,'Ali','M','2 years','Boxer','/img/dogbreeds/boxer.jpg',10.0,1,1,0,0,NULL);
INSERT or IGNORE INTO "dogs" VALUES (5,'Lisa','F','1 year','French Bulldog','/img/dogbreeds/frenchbulldog.jpg',8.0,1,0,0,0,NULL);
INSERT or IGNORE INTO "dogs" VALUES (6,'Rex','M','3 years','German Shepherd','/img/dogbreeds/germanshepherd.jpg',10.0,1,1,1,0,NULL);
INSERT or IGNORE INTO "dogs" VALUES (7,'Rosy','F','1 year','Golden Retriever','/img/dogbreeds/goldenretriever.jpg',10.0,1,1,1,0,NULL);
INSERT or IGNORE INTO "dogs" VALUES (8,'Frost','M','3 years','Jack Russell Terrier','/img/dogbreeds/jackrussellterrier.jpg',5.0,0,1,0,0,NULL);
INSERT or IGNORE INTO "dogs" VALUES (9,'Maya','F','6 years','Pug','/img/dogbreeds/pug.jpg',10.0,1,0,1,0,NULL);
INSERT or IGNORE INTO "cats" VALUES (1,'Nucco','M','2 years','Bombay','/img/catbreeds/bombay.jpg',5.0,1,0,1,0,NULL);
INSERT or IGNORE INTO "cats" VALUES (2,'Fata','F','4 years','British Shthair','/img/catbreeds/britishshthair.jpg',4.0,1,0,1,0,NULL);
INSERT or IGNORE INTO "cats" VALUES (3,'Maus','M','1 year','Egyptian Mau','/img/catbreeds/egyptianmau.jpg',3.0,0,0,1,0,NULL);
INSERT or IGNORE INTO "cats" VALUES (4,'Purrito','M','2 months','Domestic Mix','/img/catbreeds/kitten.jpg',6.0,1,0,0,0,NULL);
INSERT or IGNORE INTO "cats" VALUES (5,'Jennifurr','F','5 years','Mainecoon','/img/catbreeds/mainecoon.jpg',5.0,1,0,1,0,NULL);
INSERT or IGNORE INTO "cats" VALUES (6,'Muffin','M','10 months','Munchkin','/img/catbreeds/munchkin.jpg',7.0,1,0,1,0,NULL);
INSERT or IGNORE INTO "cats" VALUES (7,'Clawdia','F','2 years','Wirehair','/img/catbreeds/wirehair.jpg',5.0,1,0,1,0,NULL);
INSERT or IGNORE INTO "cats" VALUES (8,'Ciki','M','3 years','Tabby Domestic','/img/catbreeds/tabby.jpg',7.0,0,0,0,0,NULL);
INSERT or IGNORE INTO "cats" VALUES (9,'Garfield','M','6 years','Bengal ange','/img/catbreeds/ange.jpg',8.0,1,0,1,0,NULL);
COMMIT;
