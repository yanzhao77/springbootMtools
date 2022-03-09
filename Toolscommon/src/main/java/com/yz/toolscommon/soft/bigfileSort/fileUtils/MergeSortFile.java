package com.yz.toolscommon.soft.bigfileSort.fileUtils;

import com.yz.toolscommon.soft.sort.FailedTree;

import java.io.*;
import java.util.ArrayList;

/*
 * 多路归并
 *
 * */
public class MergeSortFile {
    public static void merge(ArrayList<File> list, String outFileName) {
        int fileSize = list.size();
        if (fileSize == 1) {
            return;
        }
        BufferedWriter out = null;
        ArrayList<String> leaves = new ArrayList<>(fileSize);
        try {
            out = new BufferedWriter(new FileWriter(outFileName));
            ArrayList<BufferedReader> inputList = new ArrayList<>();
            for (int i = 0; i < fileSize; i++) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(list.get(i))));
                inputList.add(i, reader);
            }
            String data = "";
            for (int i = 0; i < inputList.size(); i++) {
                data = inputList.get(i).readLine();
                leaves.add(data);
            }
            FailedTree<Integer> failedTree = new FailedTree(leaves);
            Integer s = failedTree.getWinner();
            out.write(failedTree.getLeaf(s) + "");
            out.newLine();
            out.flush();
            while (inputList.size() > 0) {
                String newLeaf = inputList.get(s).readLine();
                if (newLeaf == null || newLeaf.equals("")) {
                    inputList.get(s).close();
                    int remove = s;
                    inputList.remove(remove);
                    failedTree.del(s);
                } else {
                    failedTree.add(newLeaf, s);
                }
                s = failedTree.getWinner();
                if (s == null) {
                    break;
                }
                out.write(failedTree.getLeaf(s) + "");
                out.newLine();
                out.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
