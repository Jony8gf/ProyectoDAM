package com.app.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionSQLiteHelper  extends SQLiteOpenHelper {

    final String CREAR_TABLA_FRASE = "CREATE TABLE frase (id Integer, nombre TEXT, tipo TEXT, idioma TEXT)";

    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase basedatos) {

        basedatos.execSQL(CREAR_TABLA_FRASE);

        //Insertar Frases Yo Nunca en English
        basedatos.execSQL("Insert into frase values (1,'I have never consumed more than 4 different types of drugs in just one night', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (2,'I have never finished in the hospital after a party', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (3,'I have never fucked being so drunk that I wasnt able to talk', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (4,'I have never vomited on someone', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (5,'I have never embarrassed others ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (6,'I have never wanted to murder someone ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (7,'I have never touched someone lower limb and it was dirty ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (8,'I have never been more than 48 hours without sleeping ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (9,'I have never been more than 48 hours without sleeping', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (10,'I have never suffered from a trigger', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (11,'I have never had to buy the morning after pill ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (12,'I have never fucked wearing a costume ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (13,'I have never eaten an ass ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (14,'I have never made a threesome ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (15,'I have never been discovered masturbating myself  ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (16,'I have never discovered a relative masturbating him/herself ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (17,'I have never discovered a friend having sex ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (18,'I have never had sex in a public place ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (19,'I have never had sex in the sea ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (20,'I have never had sex in a shops fitting room  ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (21,'I have never had sex in a car ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (22,'I have never had sex on a motorbike', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (23,'I have never been in a orgy', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (24,'I have never been sodomized', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (25,'I have never been attracted by someone of the same sex', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (26,'I have never been attracted by someone of this group', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (27,'I have never been attracted by a teacher', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (28,'I have never been discovered being drunk when returning home ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (29,'I have never been handcuffed', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (30,'I have never been arrested by the police ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (31,'I have never stolen something of less than 100 euros ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (32,'I have never recorded myself having sex', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (33,'I have never had phone sex ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (34,'I have never had sex with someone I had just met ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (35,'I have never masturbated myself with a strange object ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (36,'I have never faked an orgasm ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (37,'I have never flirted with a friends partner ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (38,'I have never pretended to be sick not to go to work ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (39,'I have never pretended to be sick not to go to school', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (40,'I have never been attracted by a person who is more than 40 years old ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (41,'I have never stolen drinking glasses in a disco ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (42,'I have never spoken negatively about someone of this group', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (43,'I have never used sexual toys with my partner ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (44,'I have never cheated on someone  ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (45,'I have never vomited in public ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (46,'I have never done an erotic dance ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (47,'I have never thought of having a surgery of my limb  ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (48,'I have never fallen slept on a bench ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (49,'I have never fucked in the shower ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (50,'I have never got oral sex made in the shower ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (51,'I have never made oral sex in the shower ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (52,'I have never gone out and going back home before 12  ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (53,'I have never changed my degree because I didn t know what I was doing there  ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (54,'I have told a boy/ a girl that my house was free just to hace sex ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (55,'I have invited someone to watch Netflix ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (56,'I have said that I was taking an exam next week not to go party', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (57,'I have never called my ex while I was in a party', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (58,'I have never called my grandmother while I was in a party ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (59,'I have never made a private party to get high in privacy ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (60,'I have never thought of being gay or lesbian ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (61,'I have never culmed in my T-shirt  ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (62,'I have never taken a pregnancy test', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (63,'I have never masturbated myself more than three times in a day ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (64,'I have never used sexual lubricant ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (65,'I have never got a compliment for my genitals', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (66,'I have never tried more than 5 postures while having sex  ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (67,'I have never tried cybersex  ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (68,'I have never played to pretend to be another person while having sex ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (69,'I have never screamed the name of another person while having sex  ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (70,'I have never used flavoured condoms ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (71,'I have never kissed someone without knowing his/her name  ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (72,'I have never watched porn with my partner ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (73,'I have never made a streaptease ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (74,'I have never got a streaptease made ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (75,'I have never been requested to lend money to a friend  ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (76,'I have never asked a friend for money', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (77,'I have never hooked up with a celebrity ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (78,'I have never been vomited by someone ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (79,'I have never been kicked out of my house ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (80,'I have never asked someone to shit on me ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (81,'I have never had a fight with someone of my friends group', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (82,'I have never stolen someone of my friends group', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (83,'I have never fucked with someone of my friends group', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (84,'I have never measured my limb', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (85,'I have never compared my limb with others', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (86,'I have never thought of having a surgery on my tits ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (87,'I have never told my partner that I have the menstruation not to have sex  ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (88,'I have told my partner that I had a headache not to have sex with him/ her ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (89,'I have made up an excuse not to go out', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (90,'I have never thought that someone of my group is a gullible person', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (91,'I have never put a name to my genitals', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (92,'I have never been asked to cum inside ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (93,'I have never played Clash Royale', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (94,'I have never asked to be cum inside ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (95,'I have never asked for anal sex', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (96,'I have never asked my partner to have anal sex ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (97,'I have never said I wasnt going to drink again the day after the party ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (98,'I have never said I wasnt going to drink and I finished the most drunk ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (99,'I have never bought a sex toy', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (100,'I have never bought more than one thing in order not to go to the supermarket checkout with only one thing ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (101,'I have never spent a whole afternoon thinking about what to wear', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (102,'I have never looked my partners mobile phone ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (103,'I have never watched hentai', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (104,'I have never uploaded a photo on Instagram being drunk', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (105,'I have never sent a nude ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (106,'I have never received a nude ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (107,'I have never changed my clothes five minutes before going out ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (108,'I have never thought that I had a problem with alcohol ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (109,'I have never gone to an important meal having a hangover', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (110,'I have never thought of having children', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (111,'I have never played Fortnite', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (112,'I have never bought something that I didnt need', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (113,'I have never ended up with my partner through the phone ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (114,'I have never thought of getting back with my ex', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (115,'I have never judged someone without knowing him/ her ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (116,'I have never flirted through WhatsApp', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (117,'I have never flirted with films phrases ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (118,'I have never screamed because of a videogame', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (119,'I have never thought of becoming Youtuber ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (120,'I have never broken my mobile phone during a party  ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (121,'I have never thought of going to the army ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (122,'I have never drunk beer', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (123,'I have never drunk wine ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (124,'I have never drunk vodka ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (125,'I have never drunk rum ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (126,'I have never eaten pizza  ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (127,'I have never eaten tacos', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (128,'I have never smoke tobacco ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (129,'I have never smoke marijuana  ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (130,'I have never eaten hallucinogenic mushrooms  ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (131,'I have never had an Instagram account', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (132,'I have never had a Facebook account ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (133,'I have never had a SnapChat account', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (134,'I have never flirted with a dating app', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (135,'I have never run out of money in my telephone ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (136,'I have never downloaded porn in my telephone ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (137,'I have never seen porn in my telephone ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (138,'I have never searched categories in XXX webpages  ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (139,'I have never mixed different types of alcohol', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (140,'I have never lost my identity card ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (141,'I have never looked for my name on Google', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (142,'I have never looked for the average penis size ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (143,'I have never made a phone prank ', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (144,'I have never recorded myself being drunk', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (145,'I have never been fined for peeing on the street', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (146,'I have never watched a reality show', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (147,'I have never been attracted to my best friends girlfriend', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (148,'I have never read a book', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (149,'I have never playing futbol', 'YN', 'English')");
        basedatos.execSQL("Insert into frase values (150,'I have never played a Drink Games', 'YN', 'English')");
        //Reservado hasta 199

        //Insertar Frasse Yo Nunca en Español
        basedatos.execSQL("Insert into frase values (200,'Yo nunca he consumido más de 4 tipos de drogas en una noche', 'YN', 'Español')");
        //349
        basedatos.execSQL("Insert into frase values (349,'Yo nunca he consumido más de 4 tipos de drogas en una noche', 'YN', 'Español')");
        //Reservado hastas 399


        //Insertar Frases Patata Caliente en English
        basedatos.execSQL("Insert into frase values (400,'Soft drink brands', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (401,'Car brands', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (402,'The Simpsons Characters', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (403,'Cartoon', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (404,'Fast Food Brands', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (405,'Verbs', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (406,'Colors', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (407,'Footballers', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (408,'European Contries', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (409,'World capitals', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (410,'Mobile brands', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (411,'Books', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (412,'Sports', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (413,'Olympic medalists', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (414,'Racing Drivers', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (415,'Superheroes', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (416,'Foods', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (417,'Social networks', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (418,'Cities', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (419,'Numbers', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (420,'Football Teams', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (421,'Singer', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (422,'Aquatic animals', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (423,'Animals', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (424,'Series', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (425,'Netflix Series', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (426,'Films', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (427,'Actors/Actresses', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (428,'Famous', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (429,'Videogames', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (430,'Fruts', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (431,'Vegetables', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (432,'Historical figures', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (433,'Clothing brands', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (434,'Newspapers', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (435,'Clothing stores', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (436,'Construction tools', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (437,'Clothing', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (438,'Types of makeup', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (439,'TV Programs', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (440,'Movie categories', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (441,'Lenguages', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (442,'Trees', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (443,'Mountains', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (444,'Real estate', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (445,'TV Channels', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (446,'Medicines', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (447,'Elements of the periodic table', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (448,'Insects', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (449,'Ball sports', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (450,'Sauces', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (451,'Politicians', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (452,'Relative to tourism', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (453,'Table games', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (454,'Things to put in a suitcase', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (455,'Fish', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (456,'Apps', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (457,'Companies', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (458,'Supermarkets', 'PC', 'English')");
        basedatos.execSQL("Insert into frase values (459,'Countries', 'PC', 'English')");
        //Reservado hasta 499

        //Insertar Frases Patata Caliente en Español - 500
        basedatos.execSQL("Insert into frase values (500,'Marcas de Refrescos', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (501,'Marcas de Coche', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (502,'Personajes de Los Simpsons', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (503,'Dibujos Animados', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (504,'Marcas de Comida Rápida', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (505,'Verbos', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (506,'Colores', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (507,'Futbolistas', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (508,'Países Europeos', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (509,'Capitales del Mundo', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (510,'Marcas de Móviles', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (511,'Libros', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (512,'Deportes', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (513,'Medallistas Olímpicos', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (514,'Pilotos de Carreras', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (515,'Superheroes', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (516,'Comidas', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (517,'Redes Sociales', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (518,'Ciudades', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (519,'Números', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (520,'Equipos de Fútbol', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (521,'Cantantes', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (522,'Animales Acuaticos', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (523,'Animales', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (524,'Series', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (525,'Series de Netflix', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (526,'Películas', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (527,'Actores/Actrices', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (528,'Famosos', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (529,'Videojuegos', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (530,'Frutas', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (531,'Verduras', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (532,'Personajes Históricos', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (533,'Marcas de Ropa', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (534,'Periódicos', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (535,'Tiendas de ropa', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (536,'Herramientas de construcción', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (537,'Prendas de vestir', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (538,'Tipos de Maquillaje', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (539,'Programas de TV', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (540,'Categorías de Películas', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (541,'Idiomas', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (542,'Árboles', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (543,'Montañas', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (544,'Inmobiliario', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (545,'Canales de TV', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (546,'Medicamentos', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (547,'Elementos de la tabla periódica', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (548,'Insectos', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (549,'Deportes con pelota', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (550,'Salsas', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (551,'Políticos', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (552,'Relativo al turismo', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (553,'Juegos de mesa', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (554,'Cosas que meter en una maleta', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (555,'Pescados', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (556,'Apps', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (557,'Empresas', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (558,'Supermercados', 'PC', 'Español')");
        basedatos.execSQL("Insert into frase values (559,'Países', 'PC', 'Español')");
        //Reservado hasta 599

        //Insertar Frases Mimica en Español - 600

    }

    @Override
    public void onUpgrade(SQLiteDatabase basedatos, int versionAntigua, int versionNueva) {

        basedatos.execSQL("DROP TABLE IF EXISTS frase");
        onCreate(basedatos);

    }

}
