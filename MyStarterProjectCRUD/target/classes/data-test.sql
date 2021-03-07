INSERT INTO To_Do (nameofMission) 
VALUES 
('Fighting Crime'),
('Saving The World'),
('Ending Famine'),
('Saving Kittens from Trees'),
('Helping Grannies cross the Road'),
('Do Some Housework');



INSERT INTO Missions (nameofMission, responsibility, orderofimportance, completedstatus, my_To_Do_id) 
VALUES 
('Be Superman', 'Saving The World', 2, true,1),
('Help the Police', 'Crimefighting', 3, true,2),
('Buy New Cape', 'Saving The World', 1, true,3),
('End Global Warming', 'Saving The World', 2, false,4),
('Stop a Bank Robbery', 'Fighting Crime', 3, false,5),
('Complete LOTR Boxset', 'Do Some Housework', 2, false,6);