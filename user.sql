-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Mar 03, 2018 at 09:33 PM
-- Server version: 5.6.35
-- PHP Version: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `prospector-1`
--

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `user` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `enabled` tinyint(4) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `user` (`username`, `password`, `enabled`) VALUES
('dante', 'dante', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `users`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`);