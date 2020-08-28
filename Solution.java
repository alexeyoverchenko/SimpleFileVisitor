package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;

/* 
Что внутри папки?
*/
public class Solution {
         static long dir;
         static long fil;
         static long bit;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String pathIn = reader.readLine();

        if (!Files.isDirectory(Paths.get(pathIn))) {
            System.out.print(Paths.get(pathIn) + " - не папка");
        } else {
            Files.walkFileTree(Paths.get(pathIn), new SearchFileVisitor());

            System.out.println("Всего папок - " + (dir-1));
            System.out.println("Всего файлов - " + fil);
            System.out.print("Общий размер - " + bit);
        }
    }

    public static class SearchFileVisitor extends SimpleFileVisitor<Path> {


        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            Solution.dir++;
            return CONTINUE;
        }

        public FileVisitResult visitFile (Path file, BasicFileAttributes attrs) throws IOException {
            Solution.fil++;
            Solution.bit += Files.size(file);
            return CONTINUE;

        }
    }


}
