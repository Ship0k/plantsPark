--creating and populating a database

CREATE DATABASE plantsPark
GO

USE plantsPark
GO

CREATE TABLE Plants 
(Id int PRIMARY KEY IDENTITY(1,1),
Title nvarchar(30) NOT NULL)
GO

CREATE TABLE PlantsDetails
(Id int UNIQUE,
LandingDate datetime DEFAULT NULL,
ArtWorkN int DEFAULT 0,
TreatmentN int DEFAULT 0,
DestructionDate datetime DEFAULT NULL)
GO

ALTER TABLE PlantsDetails
WITH CHECK
ADD
FOREIGN KEY(ID) REFERENCES Plants (ID)
ON DELETE CASCADE
GO

CREATE TRIGGER Plants_INSERT
ON Plants
AFTER INSERT
AS INSERT PlantsDetails (Id)
SELECT Id
FROM INSERTED
GO

INSERT Plants
(Title)
VALUES
('Олива'),('Каштан'),('Клен'),('Ясень'),('Ольха'),('Ель'),('Осина'),('Акация'),('Ива'),('Липа'),
('Кедр'),('Ива'),('Сосна'),('Кедр'),('Береза'),('Ель'),('Сосна'),('Пихта'),('Олива'),('Рябина'),
('Клен'),('Акация'),('Акация'),('Ясень'),('Сосна'),('Тополь'),('Тополь'),('Береза'),('Сосна'),('Акация'),
('Баобаб'),('Сосна'),('Ясень'),('Пихта'),('Тополь'),('Ясень'),('Липа'),('Рябина'),('Сосна'),('Сосна'),
('Береза'),('Клен'),('Липа'),('Береза'),('Ель'),('Баобаб'),('Осина'),('Ива'),('Пихта'),('Сосна'),
('Тополь'),('Ива'),('Клен'),('Олива'),('Дуб'),('Дуб'),('Тополь'),('Пихта'),('Ель'),('Ясень'),
('Пихта'),('Ива'),('Ясень'),('Тополь'),('Тополь'),('Рябина'),('Ясень'),('Ясень'),('Дуб'),('Клен'),
('Сосна'),('Сосна')
GO

UPDATE PlantsDetails
SET LandingDate = '19560601', ArtWorkN = 3, TreatmentN = 2, DestructionDate = '20131103' WHERE Id = 1
UPDATE PlantsDetails
SET LandingDate = '19561006', ArtWorkN = 4, TreatmentN = 2 WHERE Id = 2
UPDATE PlantsDetails
SET LandingDate = '19580103', ArtWorkN = 2, TreatmentN = 2, DestructionDate = '20131103' WHERE Id = 3
UPDATE PlantsDetails
SET LandingDate = '19590904', ArtWorkN = 1, TreatmentN = 0, DestructionDate = '20131103' WHERE Id = 4
UPDATE PlantsDetails
SET LandingDate = '19591114', ArtWorkN = 4, TreatmentN = 1, DestructionDate = '20131103' WHERE Id = 5
UPDATE PlantsDetails
SET LandingDate = '19601202', ArtWorkN = 3, TreatmentN = 1 WHERE Id = 6
UPDATE PlantsDetails
SET LandingDate = '19610616', ArtWorkN = 4, TreatmentN = 2 WHERE Id = 7
UPDATE PlantsDetails
SET LandingDate = '19621201', ArtWorkN = 1, TreatmentN = 1 WHERE Id = 8
UPDATE PlantsDetails
SET LandingDate = '19630914', ArtWorkN = 2, TreatmentN = 0 WHERE Id = 9
UPDATE PlantsDetails
SET LandingDate = '19631019', ArtWorkN = 4, TreatmentN = 2 WHERE Id = 10
UPDATE PlantsDetails
SET LandingDate = '19631019', ArtWorkN = 3, TreatmentN = 3 WHERE Id = 11
UPDATE PlantsDetails
SET LandingDate = '19640229', ArtWorkN = 4, TreatmentN = 0 WHERE Id = 12
UPDATE PlantsDetails
SET LandingDate = '19640724', ArtWorkN = 2, TreatmentN = 2 WHERE Id = 13
UPDATE PlantsDetails
SET LandingDate = '19650417', ArtWorkN = 4, TreatmentN = 2 WHERE Id = 14
UPDATE PlantsDetails
SET LandingDate = '19650702', ArtWorkN = 3, TreatmentN = 3 WHERE Id = 15
UPDATE PlantsDetails
SET LandingDate = '19650709', ArtWorkN = 2, TreatmentN = 0 WHERE Id = 16
UPDATE PlantsDetails
SET LandingDate = '19660401', ArtWorkN = 3, TreatmentN = 2 WHERE Id = 17
UPDATE PlantsDetails
SET LandingDate = '19660702', ArtWorkN = 2, TreatmentN = 2 WHERE Id = 18
UPDATE PlantsDetails
SET LandingDate = '19661112', ArtWorkN = 2, TreatmentN = 0 WHERE Id = 19
UPDATE PlantsDetails
SET LandingDate = '19671027', ArtWorkN = 3, TreatmentN = 0 WHERE Id = 20
UPDATE PlantsDetails
SET LandingDate = '19680531', ArtWorkN = 3, TreatmentN = 2 WHERE Id = 21
UPDATE PlantsDetails
SET LandingDate = '19680914', ArtWorkN = 2, TreatmentN = 1 WHERE Id = 22
UPDATE PlantsDetails
SET LandingDate = '19690131', ArtWorkN = 0, TreatmentN = 2 WHERE Id = 23
UPDATE PlantsDetails
SET LandingDate = '19690405', ArtWorkN = 4, TreatmentN = 0 WHERE Id = 24
UPDATE PlantsDetails
SET LandingDate = '19690801', ArtWorkN = 3, TreatmentN = 2 WHERE Id = 25
UPDATE PlantsDetails
SET LandingDate = '19700116', ArtWorkN = 3, TreatmentN = 1 WHERE Id = 26
UPDATE PlantsDetails
SET LandingDate = '19700116', ArtWorkN = 4, TreatmentN = 1 WHERE Id = 27
UPDATE PlantsDetails
SET LandingDate = '19700116', ArtWorkN = 4, TreatmentN = 0 WHERE Id = 28
UPDATE PlantsDetails
SET LandingDate = '19700718', ArtWorkN = 2, TreatmentN = 0 WHERE Id = 29
UPDATE PlantsDetails
SET LandingDate = '19710508', ArtWorkN = 4, TreatmentN = 1 WHERE Id = 30
UPDATE PlantsDetails
SET LandingDate = '19711217', ArtWorkN = 3, TreatmentN = 0 WHERE Id = 31
UPDATE PlantsDetails
SET LandingDate = '19720602', ArtWorkN = 4, TreatmentN = 0 WHERE Id = 32
UPDATE PlantsDetails
SET LandingDate = '19730112', ArtWorkN = 3, TreatmentN = 1 WHERE Id = 33
UPDATE PlantsDetails
SET LandingDate = '19731222', ArtWorkN = 2, TreatmentN = 0 WHERE Id = 34
UPDATE PlantsDetails
SET LandingDate = '19731229', ArtWorkN = 2, TreatmentN = 0 WHERE Id = 35
UPDATE PlantsDetails
SET LandingDate = '19740412', ArtWorkN = 1, TreatmentN = 0 WHERE Id = 36
UPDATE PlantsDetails
SET LandingDate = '19740823', ArtWorkN = 2, TreatmentN = 0 WHERE Id = 37
UPDATE PlantsDetails
SET LandingDate = '19760227', ArtWorkN = 0, TreatmentN = 2 WHERE Id = 38
UPDATE PlantsDetails
SET LandingDate = '19770701', ArtWorkN = 2, TreatmentN = 0 WHERE Id = 39
UPDATE PlantsDetails
SET LandingDate = '19770709', ArtWorkN = 2, TreatmentN = 0 WHERE Id = 40
UPDATE PlantsDetails
SET LandingDate = '19770916', ArtWorkN = 0, TreatmentN = 0 WHERE Id = 41
UPDATE PlantsDetails
SET LandingDate = '19780107', ArtWorkN = 1, TreatmentN = 2 WHERE Id = 42
UPDATE PlantsDetails
SET LandingDate = '19780505', ArtWorkN = 1, TreatmentN = 0 WHERE Id = 43
UPDATE PlantsDetails
SET LandingDate = '19780804', ArtWorkN = 1, TreatmentN = 0 WHERE Id = 44
UPDATE PlantsDetails
SET LandingDate = '19781117', ArtWorkN = 2, TreatmentN = 0 WHERE Id = 45
UPDATE PlantsDetails
SET LandingDate = '19790615', ArtWorkN = 0, TreatmentN = 0 WHERE Id = 46
UPDATE PlantsDetails
SET LandingDate = '19810307', ArtWorkN = 3, TreatmentN = 0 WHERE Id = 47
UPDATE PlantsDetails
SET LandingDate = '19810710', ArtWorkN = 1, TreatmentN = 1 WHERE Id = 48
UPDATE PlantsDetails
SET LandingDate = '19810828', ArtWorkN = 0, TreatmentN = 0 WHERE Id = 49
UPDATE PlantsDetails
SET LandingDate = '19820102', ArtWorkN = 0, TreatmentN = 0 WHERE Id = 50
UPDATE PlantsDetails
SET LandingDate = '19820319', ArtWorkN = 1, TreatmentN = 0 WHERE Id = 51
UPDATE PlantsDetails
SET LandingDate = '19840210', ArtWorkN = 0, TreatmentN = 0 WHERE Id = 52
UPDATE PlantsDetails
SET LandingDate = '19840309', ArtWorkN = 1, TreatmentN = 1 WHERE Id = 53
UPDATE PlantsDetails
SET LandingDate = '19841116', ArtWorkN = 0, TreatmentN = 0 WHERE Id = 54
UPDATE PlantsDetails
SET LandingDate = '19850223', ArtWorkN = 1, TreatmentN = 0 WHERE Id = 55
UPDATE PlantsDetails
SET LandingDate = '19850831', ArtWorkN = 2, TreatmentN = 0 WHERE Id = 56
UPDATE PlantsDetails
SET LandingDate = '19851101', ArtWorkN = 0, TreatmentN = 0 WHERE Id = 57
UPDATE PlantsDetails
SET LandingDate = '19860208', ArtWorkN = 1, TreatmentN = 0 WHERE Id = 58
UPDATE PlantsDetails
SET LandingDate = '19860301', ArtWorkN = 0, TreatmentN = 0 WHERE Id = 59
UPDATE PlantsDetails
SET LandingDate = '19861114', ArtWorkN = 0, TreatmentN = 0 WHERE Id = 60
UPDATE PlantsDetails
SET LandingDate = '19870619', ArtWorkN = 1, TreatmentN = 1 WHERE Id = 61
UPDATE PlantsDetails
SET LandingDate = '19870711', ArtWorkN = 0, TreatmentN = 0 WHERE Id = 62
UPDATE PlantsDetails
SET LandingDate = '19871030', ArtWorkN = 0, TreatmentN = 0 WHERE Id = 63
UPDATE PlantsDetails
SET LandingDate = '19871127', ArtWorkN = 0, TreatmentN = 0 WHERE Id = 64
UPDATE PlantsDetails
SET LandingDate = '19880102', ArtWorkN = 0, TreatmentN = 0 WHERE Id = 65
UPDATE PlantsDetails
SET LandingDate = '19880220', ArtWorkN = 1, TreatmentN = 2 WHERE Id = 66
UPDATE PlantsDetails
SET LandingDate = '19880304', ArtWorkN = 0, TreatmentN = 0 WHERE Id = 67
UPDATE PlantsDetails
SET LandingDate = '19890311', ArtWorkN = 0, TreatmentN = 0 WHERE Id = 68
UPDATE PlantsDetails
SET LandingDate = '19891111', ArtWorkN = 1, TreatmentN = 0 WHERE Id = 69
UPDATE PlantsDetails
SET LandingDate = '19910405', ArtWorkN = 0, TreatmentN = 0 WHERE Id = 70
UPDATE PlantsDetails
SET LandingDate = '19920515', ArtWorkN = 1, TreatmentN = 0 WHERE Id = 71
UPDATE PlantsDetails
SET LandingDate = '19920606', ArtWorkN = 0, TreatmentN = 0 WHERE Id = 72
GO

CREATE TABLE Tasks
(Id int PRIMARY KEY IDENTITY(1,1),
TaskType varchar (50) NOT NULL)
GO

INSERT Tasks
(TaskType)
VALUES
('Высадка растения'), ('Художественная обработка'), 
('Лечение'), ('Уничтожение')
GO

CREATE TABLE ListOwnerTasks
(Id int PRIMARY KEY IDENTITY(1,1),
PlantId int NOT NULL,
TaskId int NOT NULL,
Report nvarchar(12) default 'не выполнено')
GO

ALTER TABLE ListOwnerTasks
WITH CHECK
ADD
FOREIGN KEY(PlantId) REFERENCES Plants (Id) ON DELETE CASCADE,
FOREIGN KEY(TaskId) REFERENCES Tasks (Id) ON DELETE CASCADE
GO

ALTER TABLE ListOwnerTasks
ADD
UNIQUE(PlantId)

INSERT ListOwnerTasks
(PlantId, TaskId)
VALUES
(34,2),(54,2),(23,3),(67,2),(13,3),(2,4)
GO