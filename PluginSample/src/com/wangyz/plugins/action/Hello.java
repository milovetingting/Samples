package com.wangyz.plugins.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

public class Hello extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {

        //获取当前操作工程的上下文
        Project project = e.getData(PlatformDataKeys.PROJECT);

        String title = "Hello,World!";

        //显示对话框
        Messages.showMessageDialog(project, title, title, Messages.getInformationIcon());
    }
}
