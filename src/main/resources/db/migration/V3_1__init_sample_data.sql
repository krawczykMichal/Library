insert into categories (name)
values ('Horror'),
       ('Fantasy'),
       ('Biografie'),
       ('Historia'),
       ('Biznes'),
       ('Ekonomia'),
       ('Marketing'),
       ('Dziecięca'),
       ('Młodzieżowa'),
       ('Kryminał'),
       ('Thriller'),
       ('Kulinarna'),
       ('Literatura faktu, reportaż'),
       ('Literatura piękna'),
       ('Podręczniki'),
       ('Rozwój osobisty'),
       ('Sport'),
       ('Turystyka'),
       ('Zdrowie'),
       ('Sztuka'),
       ('Religia'),
       ('Prawo'),
       ('Poradniki'),
       ('Poezja, aforyzm'),
       ('Dramat');

insert into authors (name, surname)
values ('Stephen', 'King'),
       ('H.P', 'Lovecraft'),
       ('Clive', 'Barker'),
       ('J.R.R', 'Tolkien'),
       ('George R.R', 'Martin'),
       ('Andrzej', 'Sapkowski'),
       ('Walter', 'Isaacson'),
       ('Robert A.', 'Caro'),
       ('Ron', 'Chernow'),
       ('Norman', 'Davies'),
       ('Antony', 'Beevor'),
       ('Timothy', 'Snyder'),
       ('Peter', 'Drucker'),
       ('Simon', 'Sinek'),
       ('Jim', 'Collins'),
       ('Thomas', 'Piketty'),
       ('Milton', 'Friedman'),
       ('Joseph', 'Stiglitz'),
       ('Philip', 'Kotler'),
       ('Seth', 'Godin'),
       ('Gary', 'Vaynerchuk'),
       ('Roald', 'Dahl'),
       ('J.K.', 'Rowling'),
       ('Astrid', 'Lindgren'),
       ('John', 'Green'),
       ('Suzanne', 'Collins'),
       ('Veronica', 'Roth'),
       ('Agatha', 'Christie'),
       ('Arthur Conan', 'Doyle'),
       ('Raymond', 'Chandler'),
       ('Gillian', 'Flynn'),
       ('Stieg', 'Larsson'),
       ('Lee', 'Child'),
       ('Julia', 'Child'),
       ('Jamie', 'Oliver'),
       ('Gordon', 'Ramsey'),
       ('Jon', 'krakauer'),
       ('Malcom', 'Gladwell'),
       ('Yuyal Noah', 'Harari'),
       ('Gabriel ', 'Garcia Marquez'),
       ('Leo', 'Tolstoy'),
       ('Haruki', 'Murakami'),
       ('David', 'Boddy'),
       ('Neil A', 'Campbell'),
       ('kenneth H.', 'Rosen'),
       ('Stephen R.', 'Covey'),
       ('Dale', 'Carnegie'),
       ('Tony', 'Robbins'),
       ('Michael', 'Jordan'),
       ('Phil', 'Jackson'),
       ('Andrea', 'Agassi'),
       ('Rick', 'Steves'),
       ('Lonely', 'Planet'),
       ('Bill', 'Bryson'),
       ('Atul', 'Gawande'),
       ('Michael', 'Greger'),
       ('Deepak', 'Chopra'),
       ('John', 'Berger'),
       ('Susan', 'Sontag'),
       ('Ernst', 'Gombrich'),
       ('C.S.', 'Lewis'),
       ('Karen', 'Armstrong'),
       ('Timothy', 'Keller'),
       ('Bryan A.', 'Garner'),
       ('Robert H.', 'Bork'),
       ('Alan', 'Dershowitz'),
       ('Marie', 'Kondo'),
       ('Mark', 'manson'),
       ('Brene', 'Brown'),
       ('Robert', 'Frost'),
       ('Emily', 'Dickinson'),
       ('Pablo', 'Neruda'),
       ('William', 'Shakespeare'),
       ('Tennessee', 'Williams'),
       ('Arthur', 'Miler');

insert into books (title, isbn, publisher, author_id, category_id, published_date, copies, available)
values ('The Shining', '9780385121675', 'Doubleday', 1, 1, 1977, 10, true),
       ('It', '9780450411434', 'Viking', 1, 1, 1986, 10, true),
       ('The Call of Cthulhu', '9780143129455', 'Penguin Classics', 2, 1, 1928, 10, true),
       ('At the Mountains of Madness', '9780812974416', 'Modern Library', 2, 1, 1936, 10, true),
       ('Hellbound Heart', '9780061452888', 'Perennial', 3, 1, 1986, 10, true),
       ('Books of Blood', '9780425181724', 'Berkley', 3, 1, 1984, 10, true),
       ('The Hobbit', '9780345339683', 'Ballantine Books', 4, 2, 1937, 10, true),
       ('The Lord of the Rings: The Fellowship of the Ring', '9780618640157', 'Houghton Mifflin', 4, 2, 1954, 10, true),
       ('A Game of Thrones', '9780553103540', 'Bantam Books', 5, 2, 1996, 10, true),
       ('A Clash of Kings', '9780553108033', 'Bantam Books', 5, 2, 1998, 10, true),
       ('The Last Wish', '9780316029186', 'Orbit', 6, 2, 1993, 10, true),
       ('Blood of Elves', '9780316029193', 'Orbit', 6, 2, 1994, 10, true),
       ('Steve Jobs', '9781451648539', 'Simon & Schuster', 7, 3, 2011, 10, true),
       ('Leonardo da Vinci', '9781501139154', 'Simon & Schuster', 7, 3, 2017, 10, true),
       ('The Path to Power', '9780394720951', 'Knopf', 8, 3, 1982, 10, true),
       ('Master of the Senate', '9780394720968', 'Knopf', 8, 3, 2002, 10, true),
       ('Alexander Hamilton', '9780143034754', 'Penguin Books', 9, 3, 2004, 10, true),
       ('Grant', '9780143110632', 'Penguin Press', 9, 3, 2017, 10, true),
       ('Europe: A History', '9780198201717', 'Oxford University Press', 10, 4, 1996, 10, true),
       ('Rising "44: The Battle for Warsaw', '9780333698054', 'Macmillan', 10, 4, 2003, 10, true),
       ('Stalingrad', '9780140249859', 'Penguin Books', 11, 4, 1998, 10, true),
       ('The Second World War', '9780316023757', 'Little, Brown', 11, 4, 2012, 10, true),
       ('Bloodlands: Europe Between Hitler and Stalin', '9780465002399', 'Basic Books', 12, 4, 2010, 10, true),
       ('On Tyranny', '9780804190114', 'Tim Duggan Books', 12, 4, 2017, 10, true),
       ('The Effective Executive', '9780060833459', 'HarperBusiness', 13, 5, 1967, 10, true),
       ('Innovation and Entrepreneurship', '9780060851132', 'HarperBusiness', 13, 5, 1985, 10, true),
       ('Start with Why', '9781591846444', 'Portfolio', 14, 5, 2009, 10, true),
       ('Leaders Eat Last', '9781591848011', 'Portfolio', 14, 5, 2014, 10, true),
       ('Good to Great', '9780066620992', 'HarperBusiness', 15, 6, 2001, 10, true),
       ('Built to Last', '9780060516406', 'HarperBusiness', 15, 6, 1994, 10, true),
       ('Capital in the Twenty-First Century', '9780674979857', 'Belknap Press', 16, 6, 2013, 10, true),
       ('Capital and Ideology', '9780674980822', 'Belknap Press', 16, 6, 2020, 10, true),
       ('Capitalism and Freedom', '9780226734798', 'University of Chicago Press', 17, 6, 1962, 10, true),
       ('Free to Choose', '9780156334600', 'Harcourt Brace', 17, 6, 1980, 10, true),
       ('Globalization and Its Discontents', '9780393051247', 'W.W. Norton', 18, 6, 2002, 10, true),
       ('The Price of Inequality', '9780393345063', 'W.W. Norton', 18, 6, 2012, 10, true),
       ('Marketing Management', '9780136009986', 'Pearson', 19, 7, 1967, 10, true),
       ('Principles of Marketing', '9780136079415', 'Pearson', 19, 7, 1980, 10, true),
       ('Purple Cow', '9781591843177', 'Portfolio', 20, 7, 2003, 10, true),
       ('This is Marketing', '9780525540830', 'Portfolio', 20, 7, 2018, 10, true),
       ('Crush It!', '9780061914171', 'HarperCollins', 21, 7, 2009, 10, true),
       ('Jab, Jab, Jab, Right Hook', '9780062273062', 'HarperBusiness', 21, 7, 2013, 10, true),
       ('Charlie and the Chocolate Factory', '9780142410318', 'Penguin Books', 22, 8, 1964, 10, true),
       ('Matilda', '9780142410370', 'Penguin Books', 22, 8, 1988, 10, true),
       ('Harry Potter and the Sorcerer\''s Stone', '9780590353427', 'Scholastic', 23, 8, 1997, 10, true),
       ('Harry Potter and the Chamber of Secrets', '9780439064873', 'Scholastic', 23, 8, 1998, 10, true),
       ('Pippi Longstocking', '9780142402498', 'Penguin Books', 24, 8, 1945, 10, true),
       ('The Brothers Lionheart', '9780192714282', 'Oxford University Press', 24, 8, 1973, 10, true),
       ('The Fault in Our Stars', '9780525478812', 'Dutton Books', 25, 9, 2012, 10, true),
       ('Looking for Alaska', '9780142402511', 'Penguin Books', 25, 9, 2005, 10, true),
       ('The Hunger Games', '9780439023528', 'Scholastic', 26, 9, 2008, 10, true),
       ('Catching Fire', '9780439023498', 'Scholastic', 26, 9, 2009, 10, true),
       ('Divergent', '9780062024022', 'HarperCollins', 27, 9, 2011, 10, true),
       ('Insurgent', '9780062024046', 'HarperCollins', 27, 9, 2012, 10, true),
       ('Murder on the Orient Express', '9780062693662', 'HarperCollins', 28, 10, 1934, 10, true),
       ('The ABC Murders', '9780062573292', 'HarperCollins', 28, 10, 1936, 10, true),
       ('The Hound of the Baskervilles', '9780141034365', 'Penguin Classics', 29, 10, 1902, 10, true),
       ('A Study in Scarlet', '9780141034358', 'Penguin Classics', 29, 10, 1887, 10, true),
       ('The Big Sleep', '9780394758282', 'Vintage Crime', 30, 10, 1939, 10, true),
       ('Farewell, My Lovely', '9780394758275', 'Vintage Crime', 30, 10, 1940, 10, true),
       ('Gone Girl', '9780307588364', 'Crown Publishing', 31, 11, 2012, 10, true),
       ('Sharp Objects', '9780307341556', 'Broadway Books', 31, 11, 2006, 10, true),
       ('The Girl with the Dragon Tattoo', '9780307454546', 'Knopf', 32, 11, 2005, 10, true),
       ('The Girl Who Played with Fire', '9780307454553', 'Knopf', 32, 11, 2006, 10, true),
       ('Killing Floor', '9780515141429', 'Jove', 33, 11, 1997, 10, true),
       ('Die Trying', '9780515142242', 'Jove', 33, 11, 1998, 10, true),
       ('Mastering the Art of French Cooking', '9780394721781', 'Knopf', 34, 12, 1961, 10, true),
       ('The Way to Cook', '9780394532646', 'Knopf', 34, 12, 1989, 10, true),
       ('The Naked Chef', '9781401308238', 'Hyperion', 35, 12, 1999, 10, true),
       ('Jamie\''s 30-Minute Meals', '9780718154776', 'Penguin Books', 35, 12, 2010, 10, true),
       ('Gordon Ramsay\''s Home Cooking', '9781455525256', 'Grand Central Publishing', 36, 12, 2013, 10, true),
       ('Healthy Appetite', '9781402797872', 'Sterling', 36, 12, 2008, 10, true),
       ('Into the Wild', '9780385486804', 'Villard', 37, 13, 1996, 10, true),
       ('Into Thin Air', '9780385494786', 'Villard', 37, 13, 1997, 10, true),
       ('Outliers', '9780316017930', 'Little, Brown', 38, 13, 2008, 10, true),
       ('The Tipping Point', '9780316346625', 'Little, Brown', 38, 13, 2000, 10, true),
       ('Sapiens: A Brief History of Humankind', '9780062316097', 'Harper', 39, 13, 2011, 10, true),
       ('Homo Deus', '9780062464316', 'Harper', 39, 13, 2015, 10, true),
       ('One Hundred Years of Solitude', '9780060883287', 'Harper', 40, 14, 1967, 10, true),
       ('Love in the Time of Cholera', '9780307389732', 'Knopf', 40, 14, 1985, 10, true),
       ('War and Peace', '9781400079986', 'Vintage Classics', 41, 14, 1869, 10, true),
       ('Anna Karenina', '9780143035008', 'Penguin Books', 41, 14, 1877, 10, true),
       ('Norwegian Wood', '9780375704024', 'Vintage Books', 42, 14, 1987, 10, true),
       ('Kafka on the Shore', '9781400079278', 'Vintage Books', 42, 14, 2002, 10, true),
       ('Management: An Introduction', '9780273739330', 'Pearson', 43, 15, 2009, 10, true),
       ('Management Information Systems', '9780273739842', 'Pearson', 43, 15, 2011, 10, true),
       ('Biology', '9780134093414', 'Pearson', 44, 15, 1987, 10, true),
       ('Essential Biology', '9780321772602', 'Pearson', 44, 15, 2003, 10, true),
       ('Discrete Mathematics and Its Applications', '9780073383095', 'McGraw-Hill', 45, 15, 1988, 10, true),
       ('Elementary Number Theory and Its Applications', '9780321500311', 'Pearson', 45, 15, 1984, 10, true),
       ('The 7 Habits of Highly Effective People', '9780743269513', 'Free Press', 46, 16, 1989, 10, true),
       ('The 8th Habit', '9780743287937', 'Free Press', 46, 16, 2004, 10, true),
       ('How to Win Friends and Influence People', '9780671027032', 'Simon & Schuster', 47, 16, 1936, 10, true),
       ('How to Stop Worrying and Start Living', '9780671733353', 'Simon & Schuster', 47, 16, 1948, 10, true),
       ('Awaken the Giant Within', '9780671791543', 'Free Press', 48, 16, 1991, 10, true),
       ('Unlimited Power', '9780743409391', 'Free Press', 48, 16, 1986, 10, true),
       ('For the Love of the Game', '9780609602065', 'Crown Publishing', 49, 17, 1998, 10, true),
       ('Driven from Within', '9780743284978', 'Atria Books', 49, 17, 2005, 10, true),
       ('Eleven Rings', '9780143125341', 'Penguin Books', 50, 17, 2013, 10, true),
       ('Sacred Hoops', '9780786882004', 'Hyperion', 50, 17, 1995, 10, true),
       ('Open', '9780307388407', 'Knopf', 51, 17, 2009, 10, true),
       ('Tennis Tactics', '9780736031230', 'Human Kinetics', 51, 17, 2001, 10, true),
       ('Europe Through the Back Door', '9781612388620', 'Avalon Travel', 52, 18, 1979, 10, true),
       ('Rick Steves Italy 2020', '9781641711409', 'Avalon Travel', 52, 18, 2019, 10, true),
       ('Lonely Planet Southeast Asia on a Shoestring', '9781786571208', 'Lonely Planet', 53, 18, 1975, 10, true),
       ('Lonely Planet India', '9781787013332', 'Lonely Planet', 53, 18, 1993, 10, true),
       ('A Walk in the Woods', '9780307279460', 'Broadway Books', 54, 18, 1998, 10, true),
       ('In a Sunburned Country', '9780767903868', 'Broadway Books', 54, 18, 2000, 10, true),
       ('Being Mortal', '9780805095159', 'Metropolitan Books', 55, 19, 2014, 10, true),
       ('The Checklist Manifesto', '9780312430007', 'Picador', 55, 19, 2009, 10, true),
       ('How Not to Die', '9781250066114', 'Flatiron Books', 56, 19, 2015, 10, true),
       ('How to Survive a Pandemic', '9781250796233', 'Flatiron Books', 56, 19, 2020, 10, true),
       ('Perfect Health', '9780517576745', 'Harmony', 57, 19, 1991, 10, true),
       ('The Healing Self', '9780451495525', 'Harmony', 57, 19, 2018, 10, true),
       ('Ways of Seeing', '9780140135152', 'Penguin Books', 58, 20, 1972, 10, true),
       ('About Looking', '9780679736553', 'Vintage Books', 58, 20, 1980, 10, true),
       ('On Photography', '9780312420091', 'Picador', 59, 20, 1977, 10, true),
       ('Regarding the Pain of Others', '9780312422194', 'Picador', 59, 20, 2003, 10, true),
       ('The Story of Art', '9780714832470', 'Phaidon Press', 60, 20, 1950, 10, true),
       ('Art and Illusion', '9780691097503', 'Princeton University Press', 60, 20, 1960, 10, true),
       ('Mere Christianity', '9780060652920', 'HarperOne', 61, 21, 1952, 10, true),
       ('The Screwtape Letters', '9780060652937', 'HarperOne', 61, 21, 1942, 10, true),
       ('A History of God', '9780345384560', 'Ballantine Books', 2, 21, 1993, 10, true),
       ('The Case for God', '9780307269188', 'Knopf', 62, 21, 2009, 10, true),
       ('The Reason for God', '9780525950493', 'Dutton Books', 63, 21, 2008, 10, true),
       ('Prayer', '9780143108580', 'Penguin Books', 63, 21, 2014, 10, true),
       ('The Elements of Legal Style', '9780195141627', 'Oxford University Press', 64, 22, 2002, 10, true),
       ('Garner\''s Modern English Usage', '9780190491481', 'Oxford University Press', 64, 22, 2016, 10, true),
       ('The Tempting of America', '9780684843377', 'Free Press', 65, 22, 1990, 10, true),
       ('Coercing Virtue', '9780844741520', 'AEI Press', 65, 22, 2002, 10, true),
       ('The Case for Israel', '9780471465027', 'John Wiley & Sons', 66, 22, 2003, 10, true),
       ('Taking the Stand', '9780307719293', 'Crown Publishing', 66, 22, 2013, 10, true),
       ('The Life-Changing Magic of Tidying Up', '9781607747307', 'Ten Speed Press', 67, 23, 2014, 10, true),
       ('Spark Joy', '9781607749721', 'Ten Speed Press', 67, 23, 2016, 10, true),
       ('The Subtle Art of Not Giving a F*ck', '9780062457714', 'HarperOne', 68, 23, 2016, 10, true),
       ('Everything is F*cked', '9780062880840', 'HarperOne', 68, 23, 2019, 10, true),
       ('Daring Greatly', '9781592408411', 'Gotham Books', 69, 23, 2012, 10, true),
       ('Rising Strong', '9780812995824', 'Random House', 69, 23, 2015, 10, true),
       ('The Road Not Taken', '9780140186686', 'Penguin Classics', 70, 24, 1916, 10, true),
       ('Stopping by Woods on a Snowy Evening', '9780158025801', 'Harcourt', 70, 24, 1923, 10, true),
       ('The Complete Poems of Emily Dickinson', '9780316184137', 'Little, Brown', 71, 24, 1960, 10, true),
       ('Final Harvest', '9780316184151', 'Little, Brown', 71, 24, 1964, 10, true),
       ('Twenty Love Poems and a Song of Despair', '9780143039969', 'Penguin Classics', 72, 24, 1924, 10, true),
       ('The Captain\''s Verses', '9780811217712', 'New Directions', 72, 24, 1952, 10, true),
       ('Hamlet', '9780141396507', 'Penguin Classics', 73, 25, 1603, 10, true),
       ('Macbeth', '9780141396316', 'Penguin Classics', 73, 25, 1606, 10, true),
       ('A Streetcar Named Desire', '9780811216029', 'New Directions', 74, 25, 1947, 10, true),
       ('The Glass Menagerie', '9780811214049', 'New Directions', 74, 25, 1945, 10, true),
       ('Death of a Salesman', '9780140481341', 'Penguin Books', 75, 25, 1949, 10, true),
       ('The Crucible', '9780142437339', 'Penguin Books', 75, 25, 1953, 10, true);

insert into users (name, surname, email, username, phone_number)
values ('Jan', 'Kowalski', 'Jkowalski@email.com', 'jKowalski', '+48123456789'),
       ('Anna', 'Nowak', 'anowak@email.com', 'aNowak', '+48123456780'),
       ('Piotr', 'Wiśniewski', 'pwisniewski@email.com', 'pWisniewski', '+48123456781'),
       ('Michał', 'Wójcik', 'mwojcik@email.com', 'mWójcik', '+48123456782'),
       ('Katarzyna', 'Krawczyk', 'kkrawczyk@email.com', 'kKrawczyk', '+48123456783'),
       ('Paweł', 'Zieliński', 'pzielinski@email.com', 'pZieliński', '+48123456784'),
       ('Tomasz', 'Szymański', 'tszymanski@email.com', 'tSzymański', '+48123456785'),
       ('Agnieszka', 'Lewandowska', 'alewandowska@email.com', 'aLewandowska', '+48123456786'),
       ('Marcin', 'Dąbrowski', 'mdabrowski@email.com', 'mDąbrowski', '+48123456787'),
       ('Ewa', 'Kozłowska', 'ekozlowska@email.com', 'eKozłowska', '+48123456788'),
       ('Magdalena', 'Górska', 'mgorska@email.com', 'mGórska', '+48123456789'),
       ('Jakub', 'Pawlak', 'jpawlak@email.com', 'jPawlak', '+48123456790'),
       ('Zuzanna', 'Majewska', 'zmajewska@email.com', 'zMajewska', '+48123456791'),
       ('Rafał', 'Ostrowski', 'rostrowski@email.com', 'rOstrowski', '+48123456792'),
       ('Iwona', 'Malinowska', 'imalinowska@email.com', 'iMalinowska', '+48123456793'),
       ('Krzysztof', 'Jaworski', 'kjaworski@email.com', 'kJaworski', '+48123456794'),
       ('Joanna', 'Wróbel', 'jwrobel@email.com', 'jWróbel', '+48123456795'),
       ('Marta', 'Król', 'mkrol@email.com', 'mKról', '+48123456796'),
       ('Patryk', 'Sikora', 'psikora@email.com', 'pSikora', '+48123456797'),
       ('Dorota', 'Adamczyk', 'dadamczyk@email.com', 'dAdamczyk', '+48123456798'),
       ('Wojciech', 'Baran', 'wbaran@email.com', 'wBaran', '+48123456799'),
       ('Sylwia', 'Michalak', 'smichalak@email.com', 'sMichalak', '+48123456800'),
       ('Damian', 'Borkowski', 'dborkowski@email.com', 'dBorkowski', '+48123456801'),
       ('Natalia', 'Wesołowska', 'nwesolowska@email.com', 'nWesołowska', '+48123456802'),
       ('Kamil', 'Mazur', 'kmazur@email.com', 'kMazur', '+48123456803'),
       ('Beata', 'Liszewska', 'bliszewska@email.com', 'bLiszewska', '+48123456804'),
       ('Łukasz', 'Stępień', 'lstepien@email.com', 'lStępień', '+48123456805'),
       ('Karolina', 'Pietrzak', 'kpietrzak@email.com', 'kPietrzak', '+48123456806'),
       ('Robert', 'Zając', 'rzajac@email.com', 'rZając', '+48123456807'),
       ('Paulina', 'Domańska', 'pdomanska@email.com', 'pDomańska', '+48123456808');

insert into employees (name, surname, username, email, employee_number)
values ('Jan', 'Nowak', 'jNowak', 'Jnowak@email.com', '8263501216'),
       ('Katarzyna', 'Biała', 'kBiała', 'Kbiała@email.com', '5947301502'),
       ('Jan', 'Miły', 'jMiły', 'Jmiły@email.com', '1682495032'),
       ('Paweł', 'Kowalski', 'pKowalski', 'Pkowalski@email.com', '7815492603'),
       ('Tomasz', 'Baran', 'tBaran', 'Tbaran@email.com', '0516239487'),
       ('Agnieszka', 'Nowakowska', 'aNowakowska', 'Anowakowska@email.com', '0506047892'),
       ('Ewa', 'Sikora', 'eSikora', 'Esikora@email.com', '2601549032'),
       ('Joanna', 'Malinowska', 'jMalinowska', 'Jmalinowska@email.com', '1806910682'),
       ('Marta', 'Jaworska', 'mJaworska', 'Mjaworska@email.com', '9711306068'),
       ('Tomasz', 'Olszewski', 'tOlszewski', 'Tolszewski@email.com', '9781594826'),
       ('Sebastian', 'Ogórek', 'sOgórek', 'Sogórek@email.com', '6181629182');

insert into cart (user_id)
values (1),
       (2),
       (3),
       (4),
       (5),
       (6),
       (7),
       (8),
       (9),
       (10),
       (11),
       (12),
       (13),
       (14),
       (15),
       (16),
       (17),
       (18),
       (19),
       (20),
       (21),
       (22),
       (23),
       (24),
       (25),
       (26),
       (27),
       (28),
       (29),
       (30);


insert into reservations (cart_id, reservation_date)
values (1, '2024-11-28 16:00:00'),
    (2, '2024-11-29 14:30:00'),
    (3, '2024-12-01 10:00:00'),
    (4, '2024-12-03 09:15:00'),
    (5, '2024-12-04 17:45:00'),
    (6, '2024-12-05 12:00:00'),
    (7, '2024-12-06 13:30:00'),
    (8, '2024-12-07 15:45:00'),
    (9, '2024-12-08 11:00:00'),
    (10, '2024-12-09 18:15:00'),
    (11, '2024-12-10 08:45:00'),
    (12, '2024-12-11 19:00:00'),
    (13, '2024-12-12 07:30:00'),
    (14, '2024-12-13 14:15:00'),
    (15, '2024-12-14 09:00:00'),
    (16, '2024-12-15 16:30:00'),
    (17, '2024-12-16 10:00:00'),
    (18, '2024-12-17 17:15:00'),
    (19, '2024-12-18 12:45:00'),
    (20, '2024-12-19 08:30:00'),
    (21, '2024-12-20 14:00:00'),
    (22, '2024-12-21 09:30:00'),
    (23, '2024-12-22 13:00:00'),
    (24, '2024-12-23 16:15:00'),
    (25, '2024-12-24 11:45:00'),
    (26, '2024-12-25 18:30:00'),
    (27, '2024-12-26 07:45:00'),
    (28, '2024-12-27 15:00:00'),
    (29, '2024-12-28 10:15:00'),
    (30, '2024-12-29 17:30:00');

insert into loans_request (user_id, book_id, request_date)
values (1, 1, '2024-03-03'),
       (1, 2, '2024-04-24'),
       (2, 3, '2024-05-25'),
       (2, 4, '2024-04-20'),
       (3, 5, '2024-10-07'),
       (3, 6, '2024-11-28'),
       (4, 7, '2024-07-07'),
       (4, 8, '2024-09-28'),
       (5, 9, '2024-07-05'),
       (5, 10, '2024-11-09'),
       (6, 1, '2024-12-09'),
       (6, 2, '2025-01-10'),
       (7, 3, '2024-05-30'),
       (7, 4, '2024-11-15'),
       (8, 5, '2024-08-03'),
       (8, 6, '2024-06-15'),
       (9, 7, '2024-09-03'),
       (9, 8, '2024-04-14'),
       (10, 9, '2024-04-10'),
       (10, 10, '2024-02-29'),
       (11, 1, '2024-09-13'),
       (11, 2, '2024-09-24'),
       (12, 3, '2024-04-02'),
       (12, 4, '2024-04-03'),
       (13, 5, '2025-01-10'),
       (13, 6, '2024-12-05'),
       (14, 7, '2024-12-16'),
       (14, 8, '2024-04-10'),
       (15, 9, '2024-04-11'),
       (15, 10, '2024-10-24'),
       (16, 1, '2024-03-24'),
       (16, 2, '2024-07-10'),
       (17, 3, '2024-08-01'),
       (17, 4, '2024-11-04'),
       (18, 5, '2024-09-04'),
       (18, 6, '2025-01-03'),
       (19, 7, '2024-03-03'),
       (19, 8, '2024-04-05'),
       (20, 9, '2024-12-06'),
       (20, 10, '2024-09-16'),
       (21, 1, '2024-10-08'),
       (21, 2, '2024-06-29'),
       (22, 3, '2024-05-23'),
       (22, 4, '2024-04-12'),
       (23, 5, '2024-09-23'),
       (23, 6, '2024-04-28'),
       (24, 7, '2024-06-18'),
       (24, 8, '2024-09-07'),
       (25, 9, '2024-07-12'),
       (25, 10, '2024-03-08'),
       (26, 1, '2024-04-17'),
       (26, 2, '2024-10-14'),
       (27, 3, '2024-06-30'),
       (27, 4, '2024-10-06'),
       (28, 5, '2024-11-05'),
       (28, 6, '2024-09-23'),
       (29, 7, '2024-07-05'),
       (29, 8, '2024-08-30'),
       (30, 9, '2024-11-13'),
       (30, 10, '2024-02-10');


insert into loans (loan_request_id, employee_id, loan_date, return_date, returned)
values (1, 1, '2024-10-01 10:00:00', '2024-11-01 12:00:00', false),
       (2, 2, '2024-09-01 11:00:00', '2024-10-01 13:00:00', false),
       (3, 3, '2024-10-02 09:00:00', '2024-11-02 14:00:00', false),
       (4, 4, '2024-09-02 10:30:00', '2024-10-02 15:00:00', false),
       (5, 5, '2024-10-03 12:00:00', '2024-11-03 16:00:00', false),
       (6, 6, '2024-09-03 13:00:00', '2024-10-03 17:00:00', false),
       (7, 7, '2024-10-04 14:00:00', '2024-11-04 18:00:00', false),
       (8, 8, '2024-09-04 15:00:00', '2024-10-04 19:00:00', false),
       (9, 9, '2024-10-05 16:00:00', '2024-11-05 20:00:00', false),
       (10, 10, '2024-09-05 17:00:00', '2024-10-05 21:00:00', false),
       (11, 1, '2024-10-06 08:00:00', '2024-11-06 10:00:00', false),
       (12, 2, '2024-09-06 09:00:00', '2024-10-06 11:00:00', false),
       (13, 3, '2024-10-07 12:00:00', '2024-11-07 14:00:00', false),
       (14, 4, '2024-09-07 13:00:00', '2024-10-07 15:00:00', false),
       (15, 5, '2024-10-08 14:00:00', '2024-11-08 16:00:00', false),
       (16, 6, '2024-09-08 15:00:00', '2024-10-08 17:00:00', false),
       (17, 7, '2024-10-09 16:00:00', '2024-11-09 18:00:00', false),
       (18, 8, '2024-09-09 17:00:00', '2024-10-09 19:00:00', false),
       (19, 9, '2024-10-10 08:00:00', '2024-11-10 10:00:00', false),
       (20, 10, '2024-09-10 09:00:00', '2024-10-10 11:00:00', false),
       (21, 1, '2024-10-11 10:00:00', '2024-11-11 12:00:00', false),
       (22, 2, '2024-09-11 11:00:00', '2024-10-11 13:00:00', false),
       (23, 3, '2024-10-12 14:00:00', '2024-11-12 16:00:00', false),
       (24, 4, '2024-09-12 15:00:00', '2024-10-12 17:00:00', false),
       (25, 5, '2024-10-13 16:00:00', '2024-11-13 18:00:00', false),
       (26, 6, '2024-09-13 17:00:00', '2024-10-13 19:00:00', false),
       (27, 7, '2024-10-14 08:00:00', '2024-11-14 10:00:00', false),
       (28, 8, '2024-09-14 09:00:00', '2024-10-14 11:00:00', false),
       (29, 9, '2024-10-15 10:00:00', '2024-11-15 12:00:00', false),
       (30, 10, '2024-09-15 11:00:00', '2024-10-15 13:00:00', false),
       (31, 1, '2024-10-16 14:00:00', '2024-11-16 16:00:00', false),
       (32, 2, '2024-09-16 15:00:00', '2024-10-16 17:00:00', false),
       (33, 3, '2024-10-17 16:00:00', '2024-11-17 18:00:00', false),
       (34, 4, '2024-09-17 17:00:00', '2024-10-17 19:00:00', false),
       (35, 5, '2024-10-18 08:00:00', '2024-11-18 10:00:00', false),
       (36, 6, '2024-09-18 09:00:00', '2024-10-18 11:00:00', false),
       (37, 7, '2024-10-19 10:00:00', '2024-11-19 12:00:00', false),
       (38, 8, '2024-09-19 11:00:00', '2024-10-19 13:00:00', false),
       (39, 9, '2024-10-20 14:00:00', '2024-11-20 16:00:00', false),
       (40, 10, '2024-09-20 15:00:00', '2024-10-20 17:00:00', false),
       (41, 1, '2024-10-21 16:00:00', '2024-11-21 18:00:00', false),
       (42, 2, '2024-09-21 17:00:00', '2024-10-21 19:00:00', false),
       (43, 3, '2024-10-22 08:00:00', '2024-11-22 10:00:00', false),
       (44, 4, '2024-09-22 09:00:00', '2024-10-22 11:00:00', false),
       (45, 5, '2024-10-23 10:00:00', '2024-11-23 12:00:00', false),
       (46, 6, '2024-09-23 11:00:00', '2024-10-23 13:00:00', false),
       (47, 7, '2024-10-24 14:00:00', '2024-11-24 16:00:00', false),
       (48, 8, '2024-09-24 15:00:00', '2024-10-24 17:00:00', false),
       (49, 9, '2024-10-25 16:00:00', '2024-11-25 18:00:00', false),
       (50, 10, '2024-09-25 17:00:00', '2024-10-25 19:00:00', false),
       (51, 1, '2024-10-26 08:00:00', '2024-11-26 10:00:00', false),
       (52, 2, '2024-09-26 09:00:00', '2024-10-26 11:00:00', false),
       (53, 3, '2024-10-27 10:00:00', '2024-11-27 12:00:00', false),
       (54, 4, '2024-09-27 11:00:00', '2024-10-27 13:00:00', false),
       (55, 5, '2024-10-28 14:00:00', '2024-11-28 16:00:00', false),
       (56, 6, '2024-09-28 15:00:00', '2024-10-28 17:00:00', false),
       (57, 7, '2024-10-29 16:00:00', '2024-11-29 18:00:00', false),
       (58, 8, '2024-09-29 17:00:00', '2024-10-29 19:00:00', false),
       (59, 9, '2024-10-30 08:00:00', '2024-11-30 10:00:00', false),
       (60, 10, '2024-09-30 09:00:00', '2024-10-30 11:00:00', false);


-- @TODO zastanowić się nad drugą tabelą do security dla pracowników aby oddzielić użytkowników od pracowników w bazie danych
insert into library_app_user (username, email, password, active)
values ('jKowalski', 'Jkowalski@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('aNowak', 'anowak@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('pWisniewski', 'pwisniewski@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('mWójcik', 'mwojcik@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('kKrawczyk', 'kkrawczyk@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('pZieliński', 'pzielinski@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('tSzymański', 'tszymanski@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('aLewandowska', 'alewandowska@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('mDąbrowski', 'mdabrowski@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', false),
       ('eKozłowska', 'ekozlowska@email.com', '$2a$12$YOhmtOAvoUEoDIWJal6mieKE/WU7/4Jgo.n3VR9HFFwJtnaLMt3da', true),
       ('mGórska', 'mgorska@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('jPawlak', 'jpawlak@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('zMajewska', 'zmajewska@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('rOstrowski', 'rostrowski@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('iMalinowska', 'imalinowska@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('kJaworski', 'kjaworski@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('jWróbel', 'jwrobel@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('mKról', 'mkrol@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', false),
       ('pSikora', 'psikora@email.com', '$2a$12$YOhmtOAvoUEoDIWJal6mieKE/WU7/4Jgo.n3VR9HFFwJtnaLMt3da', true),
       ('dAdamczyk', 'dadamczyk@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('wBaran', 'wbaran@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('sMichalak', 'smichalak@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('dBorkowski', 'dborkowski@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('nWesołowska', 'nwesolowska@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('kMazur', 'kmazur@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('bLiszewska', 'bliszewska@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('lStępień', 'lstepien@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', false),
       ('kPietrzak', 'kpietrzak@email.com', '$2a$12$YOhmtOAvoUEoDIWJal6mieKE/WU7/4Jgo.n3VR9HFFwJtnaLMt3da', true),
       ('rZając', 'rzajac@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('pDomańska', 'pdomanska@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('jNowak', 'Jnowak@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('kBiała', 'Kbiała@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('jMiły', 'Jmiły@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('pKowalski', 'Pkowalski@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('tBaran', 'Tbaran@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('aNowakowska', 'Anowakowska@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('eSikora', 'Esikora@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('jMalinowska', 'Jmalinowska@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', false),
       ('mJaworska', 'Mjaworska@email.com', '$2a$12$YOhmtOAvoUEoDIWJal6mieKE/WU7/4Jgo.n3VR9HFFwJtnaLMt3da', true),
       ('tOlszewski', 'Tolszewski@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('sOgórek', 'Sogórek@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true);

insert into library_app_role (role)
values ('USER'),
       ('EMPLOYEE'),
       ('ADMIN');


insert into library_app_user_role (user_id, role_id)
values (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 1),
       (6, 1),
       (7, 1),
       (8, 1),
       (9, 1),
       (10, 1),
       (11, 1),
       (12, 1),
       (13, 1),
       (14, 1),
       (15, 1),
       (16, 1),
       (17, 1),
       (18, 1),
       (19, 1),
       (20, 1),
       (21, 1),
       (22, 1),
       (23, 1),
       (24, 1),
       (25, 1),
       (26, 1),
       (27, 1),
       (28, 1),
       (29, 1),
       (30, 1),
       (31, 2),
       (32, 2),
       (33, 2),
       (34, 2),
       (35, 2),
       (36, 2),
       (37, 2),
       (38, 2),
       (39, 2),
       (40, 2),
       (41, 2);





