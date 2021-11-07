#install.packages("tidyverse")
library(tidyverse)

# Set current directory to parent directory
setwd(dirname(rstudioapi::getSourceEditorContext()$path))
setwd("..")

##################################
#                                #
#                                #
#    Data Frame and Variables    #        
#                                #
#                                #
##################################


# Read main csv file
difference_RW_and_Exclusive <- read.csv("differenceExecution.csv")
avrg_runtime_RW <- read.csv("avrgExecReadersWriters.csv")
avrg_runtime_Exclusive <- read.csv("avrgExecutionExclusive.csv")

# Axis X values
readers_amount <- NULL
readers_amount_unic <- c(0:100)

# Axis Y values
runtime_difference <- NULL
runtime_RW <- NULL
runtime_Exclusive <- NULL
average_all_runtime_RW <- NULL
average_all_runetime_Exclusive <- NULL
average_all_difference <- NULL

# Get axes X and Y values
for (column in c(1:101)) {
    runtime_difference <- append(runtime_difference, difference_RW_and_Exclusive[,column])
    runtime_RW <- append(runtime_RW, avrg_runtime_RW[, column])
    runtime_Exclusive <- append(runtime_Exclusive, avrg_runtime_Exclusive[, column])
    
    average_all_runtime_RW <- append(average_all_runtime_RW, mean(avrg_runtime_RW[, column]))
    average_all_runetime_Exclusive <- append(average_all_runetime_Exclusive, mean(avrg_runtime_Exclusive[, column]))
    average_all_difference <- append(average_all_difference, mean(difference_RW_and_Exclusive[, column]))
    
    readers_amount <- append(readers_amount, rep(column - 1, 100))
}

# All Data
all_data = data.frame(readers_amount, runtime_RW, runtime_Exclusive, runtime_difference)


##################################
#                                #
#                                #
#           Gráficos             #        
#                                #
#                                #
##################################


# Graphic [quantidade de leitores]x[tempo médio de execução para Leitores e Escritores]
plot(ggplot(data = all_data, aes(readers_amount, runtime_RW)) + 
         xlab("Número de leitores") + 
         ylab("Tempo médio de execução") +
         geom_point(size = 1, color = "red") + 
         scale_x_continuous(n.breaks = 20) + 
         scale_y_continuous(n.breaks = 15, limits = c(2.5, 9)))

# Graphic [quantidade de leitores]x[tempo médio de execução para Leitores e Escritores]
plot(ggplot(data = data.frame(readers_amount_unic, average_all_runtime_RW), aes(readers_amount_unic, average_all_runtime_RW)) + 
         xlab("Número de leitores") + 
         ylab("Tempo médio de execução") +
         geom_point(size = 1, color = "red") + 
         scale_x_continuous(n.breaks = 20) + 
         scale_y_continuous(n.breaks = 15, limits = c(2.5, 9)))

# Graphic [quantidade de leitores]x[tempo médio de execução para entidade exclusivas]
plot(ggplot(data = all_data, aes(readers_amount, runtime_Exclusive)) + 
         xlab("Número de leitores") + 
         ylab("Tempo médio de execução") +
         geom_point(size = 1, color = "blue") +
         scale_x_continuous(n.breaks = 20) + 
         scale_y_continuous(n.breaks = 15, limits = c(2.5, 9)))

# Graphic [quantidade de leitores]x[tempo médio de execução para entidade exclusivas]
plot(ggplot(data = data.frame(readers_amount_unic, average_all_runetime_Exclusive), aes(readers_amount_unic, average_all_runetime_Exclusive)) + 
         xlab("Número de leitores") + 
         ylab("Tempo médio de execução") +
         geom_point(size = 1, color = "blue") + 
         scale_x_continuous(n.breaks = 20) + 
         scale_y_continuous(n.breaks = 15, limits = c(2.5, 9)))

# Graphic [quantidade de leitores]x[diferença de tempo de execução]
plot(ggplot(data = all_data, aes(readers_amount, runtime_difference)) + 
         xlab("Número de leitores") + 
         ylab("Diferença de tempo médio de execução") +
         geom_point(size = 1) +
         scale_x_continuous(n.breaks = 20) + 
         scale_y_continuous(n.breaks = 15, limits = c(-5.2, 2.2)))

# Graphic [quantidade de leitores]x[diferença de tempo de execução]
plot(ggplot(data = data.frame(readers_amount_unic, average_all_difference), aes(readers_amount_unic, average_all_difference)) + 
         xlab("Número de leitores") + 
         ylab("Diferença de tempo médio de execução") +
         geom_point(size = 1) + 
         scale_x_continuous(n.breaks = 20) + 
         scale_y_continuous(n.breaks = 15, limits = c(-5.2, 2.2)))

# Graphic [quantidade de leitores]x[diferença de tempo de execução]
plot(ggplot(data = data.frame(readers_amount_unic, average_all_difference), aes(readers_amount_unic, average_all_difference)) + 
         xlab("Número de leitores") + 
         ylab("Diferença de tempo médio de execução") +
         geom_point(size = 1) + 
         scale_x_continuous(n.breaks = 20) + 
         scale_y_continuous(n.breaks = 15, limits = c(-0.2, 0.1)))

# Graphic [quantidade de leitores]x[tempro médio de execução para L&E, tempo médio de execução para Exclusive]
# red: Leitores e Escritores
# blue: Entidades exclusivas
plot(ggplot(data = all_data, aes(readers_amount, runtime_RW)) + 
         xlab("Número de leitores") + 
         ylab("Tempo médio de execução") +
         geom_point(size = 1, color = "red") +
         geom_point(y = runtime_Exclusive, size = 1, color = "blue") + 
         scale_x_continuous(n.breaks = 20) + 
         scale_y_continuous(n.breaks = 15, limits = c(2.5, 9)))

# Graphic [quantidade de leitores]x[tempro médio de execução para L&E, tempo médio de execução para Exclusive]
# red: Leitores e Escritores
# blue: Entidades exclusivas
plot(ggplot(data = data.frame(readers_amount_unic, average_all_runtime_RW), aes(readers_amount_unic, average_all_runtime_RW)) + 
         xlab("Número de leitores") + 
         ylab("Tempo médio de execução") +
         geom_point(size = 1, color = "red") +
         geom_point(data = data.frame(readers_amount_unic, average_all_runetime_Exclusive), y = average_all_runetime_Exclusive, size = 1, color = "blue") + 
         scale_x_continuous(n.breaks = 20) + 
         scale_y_continuous(n.breaks = 15, limits = c(2.5, 9)))

# Graphic [quantidade de leitores]x[tempro médio de execução para L&E, tempo médio de execução para Exclusive]
# red: Leitores e Escritores
# blue: Entidades exclusivas
plot(ggplot(data = data.frame(readers_amount_unic, average_all_runtime_RW), aes(readers_amount_unic, average_all_runtime_RW)) + 
         xlab("Número de leitores") + 
         ylab("Tempo médio de execução") +
         geom_point(size = 1, color = "red") +
         geom_point(data = data.frame(readers_amount_unic, average_all_runetime_Exclusive), y = average_all_runetime_Exclusive, size = 1, color = "blue") + 
         scale_x_continuous(n.breaks = 20) + 
         scale_y_continuous(n.breaks = 15, limits = c(4.19, 4.4)))

exclusive_pior <- 0

for (i in 1:length(average_all_difference)) {
    if (average_all_difference[i] < 0)
    {
        exclusive_pior <- exclusive_pior + 1
    }
}

print(paste("Quantidade de negativos:", exclusive_pior))
print(paste("Média:", median(average_all_difference)))
print(paste("Mínimo:", min(average_all_difference)))
print(paste("Máximo:", max(average_all_difference)))

difference_sorted_df <- data.frame(average_all_difference, c(0:100))
difference_sorted_df <- difference_sorted_df[order(difference_sorted_df[,1]), ]

