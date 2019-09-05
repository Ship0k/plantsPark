USE plantsPark
GO

-----------------------------Plants--------------------------------------------

-- plantTopId()
SELECT TOP 1 p.Id, Title, LandingDate, ArtWorkN, TreatmentN, DestructionDate 
FROM Plants p 
JOIN PlantsDetails pd ON pd.Id = p.Id 
ORDER BY p.Id
GO

-- plantPrev(Plant plant)
SELECT TOP 1 p.Id, Title, LandingDate, ArtWorkN, TreatmentN, DestructionDate 
FROM Plants p 
JOIN PlantsDetails pd ON pd.Id = p.Id 
WHERE p.Id < ? 
ORDER BY p.Id DESC
GO

-- plantNext(Plant plant)
SELECT TOP 1 p.Id, Title, LandingDate, ArtWorkN, TreatmentN, DestructionDate 
FROM Plants p 
JOIN PlantsDetails pd ON pd.Id = p.Id 
WHERE p.Id > ? 
ORDER BY p.Id
GO

-- byId(int id)
SELECT p.Id, Title, LandingDate, ArtWorkN, TreatmentN, DestructionDate 
FROM Plants p 
JOIN PlantsDetails pd ON pd.Id = p.Id 
WHERE p.Id = ?
GO

-- viewPlants()
SELECT p.Id, Title, LandingDate, ArtWorkN, TreatmentN, DestructionDate 
FROM Plants p 
JOIN PlantsDetails pd ON pd.Id = p.Id 
ORDER BY p.Id
GO

-- deletePlant(int idPlant)
DELETE FROM Plants WHERE Id = ?
GO

-- addPlant(String title)
INSERT Plants (Title) VALUES (?)
GO

-- replacePlant(Plant plant)
UPDATE Plants SET Title = ? WHERE ID = ? 
UPDATE PlantsDetails SET LandingDate = ? WHERE ID = ? 
UPDATE PlantsDetails SET ArtWorkN = ? WHERE ID = ? 
UPDATE PlantsDetails SET TreatmentN = ? WHERE ID = ? 
UPDATE PlantsDetails SET DestructionDate = ? WHERE ID = ?
GO
--##------------------------------------------------------------------------------

-----------------------------Forester----------------------------------------

-- viewTask()
SELECT lt.Id, Title + ' - ' + CONVERT(nvarchar(6), p.Id) Plant, TaskType, Report 
FROM ListOwnerTasks lt 
JOIN Plants p ON lt.PlantId = p.ID 
JOIN Tasks t ON lt.TaskId = t.ID 
ORDER BY lt.Id
GO

-- performTask(int idOwnerTask)
UPDATE ListOwnerTasks SET Report = '���������' WHERE Id = ?
GO
SELECT lt.Id, Title + ' - ' + CONVERT(nvarchar(6), p.Id) Plant, TaskType, Report 
FROM ListOwnerTasks lt 
JOIN Plants p ON lt.PlantId = p.ID 
JOIN Tasks t ON lt.TaskId = t.ID 
WHERE lt.Id = ?     -- returnTask                                                   
GO

-- cancelTask(int idTask) + returnTask*
UPDATE ListOwnerTasks SET Report = '�� ���������' WHERE Id = ?
GO
--##----------------------------------------------------------------------------------

-----------------------------Owner-------------------------------------------------

-- confirmExecution(int idTask)
SELECT PlantId, TaskId 
FROM ListOwnerTasks 
WHERE Id = ?
GO
UPDATE PlantsDetails SET LandingDate = CURRENT_TIMESTAMP WHERE ID = ?   -- landingSql
UPDATE PlantsDetails SET ArtWorkN = ArtWorkN + 1 WHERE ID = ?   -- artSql
UPDATE PlantsDetails SET TreatmentN = TreatmentN + 1 WHERE ID = ?   -- treatmentSql
UPDATE PlantsDetails SET DestructionDate = CURRENT_TIMESTAMP WHERE ID = ?   -- destructionSql
GO
DELETE FROM ListOwnerTasks WHERE Id = ?   -- removeTask
GO

-- selectPlant()
SELECT p.Id, Title FROM Plants p 
LEFT JOIN ListOwnerTasks l ON l.PlantId = p.Id 
WHERE l.Id IS NULL
GO

-- appointTask(int idTask, int idPlant)
INSERT ListOwnerTasks (TaskId, PlantId) VALUES (?, ?)
GO

-- deleteTask(int idTask)
DELETE FROM ListOwnerTasks WHERE Id = ?
GO
--##------------------------------------------------------------------------------------