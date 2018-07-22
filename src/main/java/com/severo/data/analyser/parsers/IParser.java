package com.severo.data.analyser.parsers;

import java.util.List;

import com.severo.data.analyser.beans.IGenericBean;

public interface IParser {
    IGenericBean parser(List<String> dados);
}
