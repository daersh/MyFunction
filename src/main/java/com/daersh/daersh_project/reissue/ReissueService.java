package com.daersh.daersh_project.reissue;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ReissueService {
    HttpServletResponse reissue(HttpServletRequest req, HttpServletResponse res);
}
