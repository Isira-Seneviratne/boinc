#! /usr/local/bin/php
<?php
    // set up a test for the windows client
    // set BOINC_PLATFORM to "windows_intelx86"

    include_once("test.inc");

    $project = new Project;
    $user = new User();
    $host = new Host($user);
    $app = new App("upper_case");
    $app_version = new App_Version($app);
    $app_version->platform_name = "windows_intelx86";
    $app_version->exec_name = "upper_case.exe";

    $project->add_user($user);
    $project->add_app($app);
    $project->add_app_version($app_version);
    $project->add_platform("windows_intelx86");
    $project->install();      // must install projects before adding to hosts

    echo "adding work\n";

    $work = new Work($app);
    $work->wu_template = "uc_wu";
    $work->result_template = "uc_result";
    $work->redundancy = 2;
    array_push($work->input_files, "input");
    $work->install($project);

    $project->start_feeder();

    echo "go run the client\n";

    //$project->stop();

    //$project->check_results(2, $result);
    //$project->compare_file("uc_wu_0_0", "uc_correct_output");
    //$project->compare_file("uc_wu_1_0", "uc_correct_output");
?>
