// Databricks notebook source
val df= spark.read.format("csv").option("header","True").load("adl://storageproject.azuredatalakestore.net/outputfolder/links.csv")

// COMMAND ----------

display(df)

// COMMAND ----------

val movies=spark.read.format("csv").option("header","True").load("adl://storageproject.azuredatalakestore.net/outputfolder/movies.csv")

// COMMAND ----------

display(movies)

// COMMAND ----------

val ratings=spark.read.format("csv").option("header","True").load("adl://storageproject.azuredatalakestore.net/outputfolder/ratings.csv")

// COMMAND ----------

display(ratings)

// COMMAND ----------

val tags=spark.read.format("csv").option("header","True").load("adl://storageproject.azuredatalakestore.net/outputfolder/tags.csv")

// COMMAND ----------

display(tags)

// COMMAND ----------


movies.createOrReplaceTempView("movies_table")

// COMMAND ----------

// MAGIC %sql
// MAGIC select * from movies_table

// COMMAND ----------

ratings.createOrReplaceTempView("ratings_table")

// COMMAND ----------

// MAGIC %sql
// MAGIC select count(*) from ratings_table

// COMMAND ----------

tags.createOrReplaceTempView("tags_table")

// COMMAND ----------

// MAGIC %sql
// MAGIC select count(*) from tags_table

// COMMAND ----------

// MAGIC %sql
// MAGIC select * from movies_table left join ratings_table on movies_table.movieId=ratings_table.movieId inner join tags_table on movies_table.movieId=tags_table.movieId

// COMMAND ----------

// MAGIC %sql
// MAGIC select distinct movieId,title,rating,tag from (select * from(select movies_table.movieId,movies_table.title,ratings_table.rating,tags_table.tag from movies_table left join ratings_table on movies_table.movieId=ratings_table.movieId left join tags_table on movies_table.movieId=tags_table.movieId)) limit 100

// COMMAND ----------

// MAGIC %sql
// MAGIC select * from (select movies_table.movieId, movies_table.title, ratings_table.rating,ratings_table.timestamp from movies_table left join ratings_table on movies_table.movieId=ratings_table.movieId) where rating=5 order by timestamp desc limit 100

// COMMAND ----------

// MAGIC %sql
// MAGIC select count(1) from ratings_table

// COMMAND ----------


