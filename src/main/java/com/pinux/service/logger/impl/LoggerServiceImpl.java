package com.pinux.service.logger.impl;

import com.pinux.entity.logger.Logger;
import com.pinux.mapper.logger.LoggerMapper;
import com.pinux.service.logger.LoggerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pinux
 * @since 2019-11-06
 */
@Service
public class LoggerServiceImpl extends ServiceImpl<LoggerMapper, Logger> implements LoggerService {

}
