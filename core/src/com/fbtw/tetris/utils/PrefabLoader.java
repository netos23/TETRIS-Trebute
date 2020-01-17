package com.fbtw.tetris.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.fbtw.tetris.objects.Part;

import java.util.StringTokenizer;



public class PrefabLoader {

    private int [][][] parts ;
    private int [] isRotaate;

    public PrefabLoader(String fileName){
        FileHandle handler = Gdx.files.internal(fileName);
        StringTokenizer tokenizer = new StringTokenizer(handler.readString());

        int partsCount = Integer.parseInt(tokenizer.nextToken());

        parts = new int[partsCount][][];
        isRotaate = new int[partsCount];

        for(int i=0;i<partsCount;i++){
            int n = Integer.parseInt(tokenizer.nextToken());
            isRotaate[i]= Integer.parseInt(tokenizer.nextToken());
            parts[i] = new int[n][n];
            for(int j=0;j<n;j++){
                for(int k=0;k<n;k++){
                    parts[i][j][k] = Integer.parseInt(tokenizer.nextToken());
                }
            }
        }
    }

    public Part[] getPrefabs()throws Exception{
        Part[] result = new Part[parts.length];

        for(int i=0;i<parts.length;i++){
            result[i] = new Part(parts[i],-100,-100,isRotaate[i]==1);
        }

        return result;
    }

}
